<template>
    <div class="stock">
        <h1>Stock</h1>
        <StockSearch></StockSearch>
    </div>
</template>

<script>
    import StockSearch from "@/components/StockSearch";

    export default {
        name: 'stock',
        components: {
            StockSearch
        }
    }
</script>

<style scoped>

</style>