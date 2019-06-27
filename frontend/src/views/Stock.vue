<template>
    <div class="stock">
        <h1>Stock</h1>
        <div class="stock-search container">
            <div class="row">
                <div class="col">
                    <label><input class="stock-symbol" v-model="stockSymbol"></label>
                    <button @click="fetchStock" class="btn btn-primary" type="button">Search</button>
                </div>
            </div>
        </div>
        <div class="stock-result input-group">
            <div class="stock-data">
                {{ stockData }}
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Stock.vue",
        data() {
            return {
                stockSymbol: '',
                stockData: ''
            }
        },
        methods: {
            async fetchStock() {
                try {
                    this.stockData = await this.$http.get('http://localhost:8080/stock/' + this.stockSymbol)
                } catch (err) {
                    console.log(err);
                }
            }
        }
    }
</script>

<style scoped>

</style>