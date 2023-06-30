<template>
    <div class="w-100 d-flex align-items-center justify-content-between">
        <div class="text-start">
            <h5 class="mb-0 mt-3">{{accountTitle || "Loading..."}}</h5>
            <p class="money-card-text">{{accountType || "Loading..."}}</p>
        </div>
        <div>
            <div class="d-flex justify-content-left align-items-center align-self-start">
                <h5 class="mb-0">{{balance || "Loading..."}}</h5>
                <p :class="profit(balancePercentage) ? 'text-success' : 'text-danger'" class="ms-2 mb-0 font-weight-medium">{{ balancePercentage || "Loading... "}}</p>
            </div>
            <p class="text-end money-card-text">Balance</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'OverviewItem',
    props: {
        data : {}
    },
    methods:{
        profit(val){
            if (val){
            return val.includes('+');
            }
        }
    },
    computed : {
        accountTitle(){
            if (this.data.accountID){
                return this.data.accountID + " - " +(this.data.accountSize / 1000) + "K"
            }
        },
        accountType(){
            if (this.data.accountType){
                if (this.data.accountType === "LIVE"){
                    return "Evaluation Funded Live"
                } else if (this.data.accountType === "PHASE 2"){
                    return "Evaluation Phase 2 Demo"
                } else {
                    return "Evaluation Phase 1 Demo"
                }
            }
        },
        balance(){
            if (this.data.balance){
               return '$' + this.data.balance.toLocaleString()
            }
        },
         balancePercentage(){
            if (this.data.balance && this.data.accountSize){
                let pnl = this.data.balance - this.data.accountSize
                if (pnl < 0){
                    return '-' + Math.abs((pnl/this.data.accountSize) * 100)
                        .toFixed(2)
                        .toLocaleString() + "%"
                }else{
                    return '+' + Math.abs((pnl/this.data.accountSize) * 100)
                        .toFixed(2)
                        .toLocaleString() + "%"
                }
            }
        }
    }
}
</script>


