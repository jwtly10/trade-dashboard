<template>
    <h1>Base Stats for account {{this.account.accountID + ' - ' +this.account.accountType}}</h1>
<div class="row">
    <div class="col">
        <Stat :data="[
            {title:'Starting Overall Balance',value:statsAccountSize}]"
        />
    </div>
    <div class="col">
        <Stat :data="[
            {title:'Net Profit / Loss',value:statsGetPnl}]"
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
            {title:'Average Return',value:statsAverageReturn},
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
    components:{
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
    computed:{
        statsAverageReturn(){
            return "$" + (((this.stats.wonTrades * this.stats.averageWin) + (this.stats.lostTrades * this.stats.averageLoss) ) / this.stats.trades).toFixed(2)
        },
        statsWinRate(){
            if (this.stats.wonTradesPercent) {
               return this.stats.wonTradesPercent.toFixed(2) + '%'
            }
        },
        statsWins(){
            if (this.stats.wonTrades){
                return this.stats.wonTrades
            }
        },
        statsAccountSize(){
            return '$' + this.account.accountSize.toLocaleString()
        },
        statsTrades(){
            if (this.stats.trades){
                return this.stats.trades
            }
        },
        statsGetPnl(){
                if (this.stats.balance && this.account.accountSize){
                    let pnl = this.stats.balance - this.account.accountSize
                    if (pnl < 0){
                        return '-$' + Math.abs(pnl).toLocaleString()
                    }else{
                        return '$' + Math.abs(pnl).toLocaleString()
                    }
                }
        }
    },
    data(){
        return {
           stats : {
           },
        }
    },
    created() {
        this.getStats()
    },
    watch:{
        account(){
            this.getStats()
        }
    }
}
</script>


