package dv2.portfolioservice.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dv2.portfolioservice.controller.payload.PortfolioCreationRequest;
import dv2.portfolioservice.domain.Portfolio;
import dv2.portfolioservice.service.PortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;

import java.util.List;
import java.util.Optional;

import static spark.Spark.*;

public class PortfolioController {
    private final static Logger LOGGER = LoggerFactory.getLogger(PortfolioController.class);

    private final PortfolioService portfolioService;
    private final ObjectMapper mapper = new ObjectMapper();

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    private static String requestInfoToString(Request request) {
        return request.requestMethod() +
                " " + request.url() +
                " " + request.body();
    }

    public void defineResources() {
        before((request, response) -> {
            response.type("application/json");
            LOGGER.info(requestInfoToString(request));
        });

        get("/:portfolioId", (req, res) -> {
            String portfolioId = req.params(":portfolioId");
            if (portfolioId != null) {
                Optional<Portfolio> portfolio = portfolioService.findPortfolioById(portfolioId);
                if (portfolio.isPresent()) {
                    return portfolio.get();
                } else {
                    res.status(400);
                }
            } else {
                res.status(404);
            }
            return "";
        });

        get("/", (req, res) -> {
            List<Portfolio> portfolioList = portfolioService.listPortfolios();
            return mapper.writeValueAsString(portfolioList);
        });

        post("/", (req, res) -> {
            try {
                PortfolioCreationRequest portfolioCreationRequest = mapper.readValue(req.body(), PortfolioCreationRequest.class);
                String id = portfolioService.createPortfolio(portfolioCreationRequest);
                res.status(200);
                return id;
            } catch (JsonParseException e) {
                res.status(400);
                return "";
            }
        });
    }
}
