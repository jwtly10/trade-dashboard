<template>
    <div class="card d-inline-flex flex-lg-row flex-column bg-dark w-100 mb-3">
        <div v-for="stat in stats" class="card-body">
            <div class="d-flex justify-content-center align-items-center align-self-start">
                <h3 class="money-card-title mb-0">{{ moneyFormat(stat) }}</h3>
                <p
                v-bind:class="[stat.percentage < 0 ? 'colorLoss' : 'colorWin', 'ms-2', 'mb-0', 'font-weight-medium']">
                    {{ percentageFormat(stat) }}</p>
            </div>
            <p class="money-card-text">{{ stat.header }}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'MoneyStats',
    props: {
        stats : [Object]
    },
    methods: {
        moneyFormat (stat) {
            return "$" + stat.value.toFixed(2)
        },
        percentageFormat (stat){
            if (stat.percentage > 0){
                return "+" + stat.percentage.toFixed(1) + '%'
            } else {
               return stat.percentage.toFixed(1) + '%'
            }
        }
    },
    data(){
        return {
            
        }
    }

}
</script>


