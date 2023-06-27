<template>
    <h1>Base Stats for account {{this.accountID + ' - ' +this.accountType}}</h1>
<div class="row">
    <div class="col">
        <Stat :data="[
            {title:'Starting Overall Balance',value:statsAccountSize}]"
        />
    </div>
    <div class="col">
        <Stat :data="[
            {title:'Net Profit / Loss',value:statsGetBalance}]"
        />
    </div>
    <div class="col-4">
        <Stat :data="[
            {title:'Average Return Per Trade',value:statsAverageReturn},
            {title:'Win Rate',value:statsWinRate}]"
        />
    </div>
    <div class="col-6">
        <Stat :data="[
            {title:'Average Return Per Trade',value:statsAverageReturn},
            {title:'Win Rate',value:statsWinRate}]"
        />
    </div>
    <div class="col-sm-6">
        <Stat :data="[
            {title:'Average Return Per Trade',value:statsAverageReturn},
            {title:'Win Rate',value:statsWinRate}]"
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
        accountKey : String,
        accountSize: Number,
        accountID : Number,
        accountType: String,
        created : undefined
    },
    methods: {
        async getStats() {
            await MetaStatsService.getStats(this.accountKey).then((response) => {
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
        statsAccountSize(){
            return '$' + this.accountSize.toLocaleString()
        },
        statsGetBalance(){
                if (this.stats.balance){
                    return '$' + this.stats.balance.toLocaleString()
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
    }
}
</script>


