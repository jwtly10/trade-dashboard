<template>
    <div class="card">
        <div class="card-body p-4">
            <div class="d-flex flex-row justify-content-between">
                <div>
                    <h4 class="card-title pb-4">Accounts Overview</h4>
                </div>
            </div>
            <div v-for="(stat, index) in accountStats" class="row">
                <OverviewItem v-bind:data="stat"/>
                <hr class="m-0" v-if="index < accountStats.length - 1">
            </div>
        </div>
    </div>

</template>

<script>
import OverviewItem from "@/components/OverviewItem.vue";
import OverviewService from "@/services/overviewService";

export default {
    name: 'OverviewList',
    components: {OverviewItem},
    props: {

    },
    data(){
        return{
            accountStats : [Object]
        }
    },
    methods:{
        async getOverview() {
            await OverviewService.getOverview().then((response) => {
                this.accountStats = response.data
            })
        },
    },
    created(){
        this.getOverview()
    }
}
</script>


