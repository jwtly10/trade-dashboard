<template>
    <div class="container">
        <h2 class="text-center">
            Trade List
        </h2>
        <table class="table">
            <thead>
            <th>Ticket ID</th>
            <th>Account ID</th>
            <th>Symbol</th>
            <th>Profit</th>
            </thead>
            <tbody>
            <tr v-for="trade in trades" v-bind:key="trade.ticketID">
                <td>{{ trade.ticketID }}</td>
                <td>{{ trade.accountID }}</td>
                <td>{{ trade.symbol }}</td>
                <td>{{ trade.profit }}</td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import TradeService from '../services/tradeService'

export default {
    name: 'Trades',
    data() {
        return {
            trades: []
        }
    },
    methods: {
        getTrades() {
            TradeService.getTrades(10134834).then((response) => {
                this.trades = response.data
            });
        }
    },
    created() {
        this.getTrades()
    }
}

</script>
