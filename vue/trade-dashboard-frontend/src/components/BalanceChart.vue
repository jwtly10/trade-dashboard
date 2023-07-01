<template>
    <Doughnut id="my-chart-id" :options="chartOptions" :data="chartData" />
</template>

<script>
import {Doughnut} from 'vue-chartjs'
import {ArcElement, Chart as ChartJS, Legend, Tooltip} from 'chart.js'

ChartJS.register(ArcElement, Tooltip, Legend)

export default {
    name: 'BalanceChart',
    components: { Doughnut },
    props: {
        chartData: {
            type: Object,
            required: true
        },
    },
    data() {
        return {
            chartOptions: {
                responsive: false
            }
        }
    },
    methods: {
    }
}
</script>
