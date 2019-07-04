<template>
    <div>
        <div class="input-group mb-3">
            <input class="form-control" placeholder="Portofolio name" type="text" v-model="portfolioName">
            <div class="input-group-append">
                <button @click="createPortfolio" class="btn btn-primary btn-sm" type="button">Create</button>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col">
                <div class="alert-success" v-show="showSuccessfulCreation">Portfolio Created!</div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PortfolioCreation",
        data() {
            return {
                portfolioName: '',
                showSuccessfulCreation: false
            }
        },
        methods: {
            async createPortfolio() {
                try {
                    this.showSuccessfulCreation = false;
                    const response = await this.$http.post('http://localhost:8081/portfolio-service/portfolios', {portfolioName: this.portfolioName});
                    if (response.status === 200) {
                        this.showSuccessfulCreation = true
                    }
                } catch (e) {
                    console.log(e);
                }
            }
        }
    }
</script>

<style scoped>

</style>