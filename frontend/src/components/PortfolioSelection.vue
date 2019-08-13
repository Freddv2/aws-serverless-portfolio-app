<template>
    <div class="row">
        <div class="col">
            <b-form-select :options="portfolios" v-model="selectedPortfolioId"></b-form-select>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PortfolioSelection",
        data() {
            return {
                selectedPortfolioId: null,
                portfolios: [],
            }
        },
        mounted() {
            this.fetchPortfolios();
        },
        methods: {
            async fetchPortfolios() {
                try {
                    const response = await this.$http.get('/portfolios');
                    if (response.status === 200) {
                        this.portfolios = response.data.map((port) => {
                            return {
                                value: port.id,
                                text: port.name
                            }
                        });
                        if (this.portfolios)
                            this.selectedPortfolioId = this.portfolios[0].value;
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