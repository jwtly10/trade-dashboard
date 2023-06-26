<template>
    <div class="text-center" id="app">
        <div class="container text-center">
            <div class="row">
                <div class="row">
                    <div class="col">
                        <MoneyStats v-bind:stats="pnl"/>
                    </div>
                    <div class="col">
                        <MoneyStats v-bind:stats="pnl"/>
                    </div>
                    <div class="col">
                        <MoneyStats v-bind:stats="pnl"/>
                    </div>
                    <div class="col-12">
                        <MoneyStats v-bind:stats="stats"/>
                    </div>
                </div>
                <div class="row">
                    <Trades />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Trades from './components/Trades.vue'
import MoneyStats from "@/components/MoneyStats.vue";

export default {
    name: 'App',
    components: {
        MoneyStats,
        Trades
    },
    data() {
        return {
            pnl: [{
                header: 'Daily PNL',
                value: 576.49,
                percentage: 3.3
            }],
            stats: [{
                header: 'Net Profit / Loss',
                value: 256.4,
                percentage: -2.3
            }, {
                header: 'Daily Drawdown Breach',
                value: 10043.3,
                percentage: -5.0
            }]
        }
    }
}

</script>

