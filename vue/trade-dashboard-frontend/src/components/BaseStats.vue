<template>
  <!--    <h2>Base Stats for account {{this.account.accountID + ' - ' +this.account.accountType}}</h2>-->
    <div class="row">
        <div class="col">
            <Stat :data="[
            {title:'Starting Overall Balance',value:statsAccountSize}]"
            />
        </div>
        <div class="col">
            <Stat :data="[
            {title:'Net Profit / Loss',value:statsGetPnl,extra:statsGetPnlPercentage}]"
            />
        </div>
        <div class="col-6">
            <Stat :data="[
            {title:'Average Return',value:statsAverageReturn},
            {title:'Win Rate',value:statsWinRate},
            {title:'Win Rate',value:statsWinRate}]"
            />
        </div>
        <div class="col-6 col-lg-9">
            <Stat :data="[
            {title:'Number of Trades',value:statsTrades},
            {title:'Number of Wins',value:statsWins},
            {title:'Win Rate',value:statsWinRate}]"
            />
        </div>
        <div class="col">
            <Stat :data="[
            {title:'Days Since First Trade',value:statsGetDaysSinceFirstTrade},
            ]"
            />
        </div>

    </div>
</template>

<script>
import MetaStatsService from "@/services/metaStatsService";
import Stat from "@/components/Stat.vue";

export default {
    name: 'BaseStats',
    components: {
        Stat
    },
    props: {
        account: Object
    },
    methods: {
        async getStats() {
            await MetaStatsService.getStats(this.account.accountKey).then((response) => {
                this.stats = response.data
            })
        },
    },
    computed: {
        statsAverageReturn() {
            if (this.stats.wonTrades && this.stats.averageWin && this.stats.lostTrades && this.stats.averageLoss) {
                return "$" + (((this.stats.wonTrades * this.stats.averageWin)
                        + (this.stats.lostTrades * this.stats.averageLoss))
                    / this.stats.trades).toFixed(2)
            }
        },
        statsWinRate() {
            if (this.stats.wonTradesPercent) {
                return this.stats.wonTradesPercent.toFixed(2) + '%'
            }
        },
        statsGetDaysSinceFirstTrade() {
            if (this.stats.daysSinceTradingStarted) {
                return this.stats.daysSinceTradingStarted.toFixed(0) + " Days"
            }
        },
        statsWins() {
            if (this.stats.wonTrades) {
                return this.stats.wonTrades
            }
        },
        statsAccountSize() {
            if (this.account.accountSize) {
                return '$' + this.account.accountSize.toLocaleString()
            }
        },
        statsTrades() {
            if (this.stats.trades) {
                return this.stats.trades
            }
        },
        statsGetPnl() {
            if (this.stats.balance && this.account.accountSize) {
                let pnl = this.stats.balance - this.account.accountSize
                if (pnl < 0) {
                    return '-$' + Math.abs(pnl).toLocaleString()
                } else {
                    return '$' + Math.abs(pnl).toLocaleString()
                }
            }
        },
        statsGetPnlPercentage() {
            if (this.stats.balance && this.account.accountSize) {
                let pnl = this.stats.balance - this.account.accountSize
                if (pnl < 0) {
                    return '-' + Math.abs((pnl / this.account.accountSize) * 100)
                        .toFixed(2)
                        .toLocaleString() + "%"
                } else {
                    return '+' + Math.abs((pnl / this.account.accountSize) * 100)
                        .toFixed(2)
                        .toLocaleString() + "%"
                }
            }
        }
    },
    data() {
        return {
            stats: {},
            loading: false
        }
    },
    created() {
        this.getStats()
    },
    watch: {
        account() {
            this.getStats()
        }
    }
}
</script>


