<template>
    <div class="portfolio container">
        <h1>Portfolio</h1>
        <div class="row">
            <div class="col">
                <b-form-select v-model="selectedPortfolioId">
                    <option v-for="(name,id) in portfolios"></option>
                </b-form-select>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Portfolio",
        data() {
            return {
                selectedPortfolioId: null,
                portfolios: []
            }
        },
        mounted() {
            this.fetchPortfolios();
        },
        methods: {
            async fetchPortfolios() {
                try {
                    const response = await this.$http.get('http://localhost:8081/portfolio-service/portfolios/');
                    if (response.status === 200) {
                        this.portfolios = response.data;
                    }
                } catch (err) {
                    console.log(err);
                }
            }
        }
    }
</script>

<style scoped>

</style>