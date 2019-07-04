<template>
    <div class="portfolio container">
        <h1>Portfolio</h1>
        <PortfolioCreation/>
        <PortfolioSelection/>
    </div>
</template>

<script>
    import PortfolioCreation from "@/components/PortfolioCreation";
    import PortfolioSelection from "@/components/PortfolioSelection";

    export default {
        name: "Portfolio",
        components: {PortfolioCreation, PortfolioSelection},
    }
</script>

<style scoped>

</style>