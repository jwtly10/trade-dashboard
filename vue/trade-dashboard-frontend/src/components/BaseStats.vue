<template>
<div class="row">
    <div class="col">
        <Stat :data="[
            {title:'Starting Overall Balance',value:accountSizec}]"
        />
    </div>
    <div class="col-4">
        <Stat :data="[
            {title:'Average Return Per Trade',value:averageReturn},
            {title:'Win Rate',value:winrate}]"
        />
    </div>
    <div class="col">
        <Stat :data="[
            {title:'Average Return Per Trade',value:averageReturn},
            {title:'Win Rate',value:winrate}]"
        />
    </div>
    <div class="col">
        <Stat :data="[
            {title:'Average Return Per Trade',value:averageReturn},
            {title:'Win Rate',value:winrate}]"
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
        accountSize: Number
    },
    methods: {
        async getStats() {
            await MetaStatsService.getStats(this.accountKey).then((response) => {
                this.stats = response.data
            })
        },
    },
    computed:{
        averageReturn(){
            return "$" + (((this.stats.wonTrades * this.stats.averageWin) + (this.stats.lostTrades * this.stats.averageLoss) ) / this.stats.trades).toFixed(2)
        },
        winrate(){
            if (this.stats.wonTradesPercent) {
               return this.stats.wonTradesPercent.toFixed(2) + '%'
            }
        },
        accountSizec(){
            return '$' + this.accountSize.toLocaleString()
        }
    },
    data(){
        return {
           stats : {
           }
        }
    },
    created() {
        this.getStats()
    }
}
</script>


