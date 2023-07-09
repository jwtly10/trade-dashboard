<template>
    <div class="card">
        <div class="card-body p-4">
            <div class="d-flex flex-row justify-content-between">
                <div>
                    <h5 class="card-title pb-4 text-start">Accounts Overview</h5>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" v-model="equityFlag" value="" id="equityFlagCB">
                    <label for="equityFlagCB" class="h6 w-100 form-check-label">Show Equity</label>
                </div>
            </div>
            <div v-for="(stat, index) in accountStats" class="row">
                <OverviewItem :equityFlag="equityFlag" :data="stat"/>
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
        accountStats: [Object],
    },
    data() {
        return {
            equityFlag: false
        }
    },
    methods: {
        async getOverview() {
            await OverviewService.getOverview().then((response) => {
                this.accountStats = response.data
            })
        },
    },
    created() {
        // this.getOverview()
    }
}
</script>


