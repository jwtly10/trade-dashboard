<template>
    <div class="content-wrapper">
        <div class="row">
            <div class="col-sm mb-3">
                <div class="card">
                    <div class="card-body p-4 balchart">
                        <div>
                            <h4 class="card-title pb-4 text-start">Live Account Profit </h4>
                        </div>
                        <div class="row">
                            <div class="col mb-3 d-flex justify-content-center">
                                <BalanceChart v-if="loaded && accountIDs.length !== 0" :chartData="chartData"/>
                                <p v-else>No live accounts in profit yet</p>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <h3 v-if="loaded && accountIDs.length !== 0">Total Profit : {{ liveProfit }}</h3>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm">
                <OverviewList v-bind:accountStats="accountStats"/>
            </div>
        </div>
    </div>
</template>
<script>

import BalanceChart from "@/components/BalanceChart.vue";
import OverviewList from "@/components/OverviewList.vue";
import OverviewService from "@/services/overviewService";
import testData from '@/assets/testData.js'

export default {
    name: 'Overview',
    components: {BalanceChart, OverviewList},
    props: {},
    data() {
        return {
            loaded: false,
            accountStats: [Object],
            chartData: {},
            accountIDs: [],
            testData: testData.getTestData
        }
    },
    methods: {
        getRandomColor() {
            let letters = '0123456789ABCDEF'.split('')
            let color = '#'
            for (var i = 0; i < 6; i++) {
                color += letters[Math.floor(Math.random() * 16)]
            }
            return color
        },
        loadChartData() {
            let dataSets = {
                data: [],
                backgroundColor: []
            }

            this.accountStats.forEach(account => {
                if ((account.balance - account.accountSize) > 0 && account.accountType === 'LIVE') {
                    let accountName = account.accountID + " "
                        + (account.accountSize / 1000) + "K " + account.accountType
                    this.accountIDs.push(accountName)
                    dataSets.backgroundColor.push(this.getRandomColor())
                    dataSets.data.push(parseInt((account.balance - account.accountSize).toFixed(0)))
                }
            })

            this.chartData.labels = this.accountIDs
            this.chartData.datasets = [dataSets]
        },
        async getOverview() {
            await OverviewService.getOverview().then((response) => {
                this.accountStats = response.data
                this.accountStats = testData.getTestData()
                this.loadChartData()
                this.loaded = true;
            })
        },
    },
    computed: {
        liveProfit() {
            let profit = 0
            this.accountStats.forEach(account => {
                let x = account.balance - account.accountSize
                profit = x > 0 && account.accountType === 'LIVE'
                    ? profit + x
                    : profit
            })
            return '$' + profit.toFixed(2).toLocaleString()
        }
    },
    created() {
        this.getOverview()
    }
}
</script>


