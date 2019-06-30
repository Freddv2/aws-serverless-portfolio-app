<template>
    <div>
        <div class="stock-search container">
            <div class="row">
                <div class="col">
                    <label><input class="stock-symbol" v-model="symbol"></label>
                    <button @click="fetchStock" class="btn btn-primary" type="button">Search</button>
                </div>
            </div>
        </div>
        <div class="stock-result">
            <ul class="list-group list-group-flush ">
                <li class="list-group-item" style="border: none">
                    <strong>Name</strong>: {{name}}
                </li>
                <li class="list-group-item" style="border: none">
                    <strong>Price</strong>: {{price}}
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'StockSearch',
        data() {
            return {
                symbol: '',
                name: '',
                price: null
            }
        },
        methods: {
            async fetchStock() {
                try {
                    const response = await this.$http.get('http://localhost:8080/stock-service/stocks/' + this.symbol);
                    if (response.status === 200) {
                        this.name = response.data.name;
                        this.price = response.data.price;
                    } else {
                        console.log('Stock not found')
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