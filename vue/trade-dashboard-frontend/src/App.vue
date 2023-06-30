<template>
    <div class="text-center" id="app">
        <nav class="navbar bg-body-tertiary mb-5">
            <div class="container">
                <span class="navbar-brand mb-0 h1">trade-dashboard-frontend</span>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" v-model="showOverview" value="" id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">Show Overview</label>
                </div>
                <AccountPicker :accounts="accounts" :account="selectedAccount" v-model="selectedAccount"/>
            </div>
        </nav>
        <div v-if="showOverview">
            LALAL
        </div>
        <div v-else class="container text-center">
            <div class="row">
                <BaseStats v-bind:account="selectedAccount"/>
                <div class="row">
                    <Trades />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Trades from './components/Trades.vue'
import BaseStats from "@/components/BaseStats.vue";
import AccountPicker from "@/components/AccountPicker.vue";
import AccountService from "@/services/accountService";

export default {
    name: 'App',
    components: {
        BaseStats,
        Trades,
        AccountPicker
    },
    methods: {
       async getAccounts(){
          await AccountService.getAccounts().then((response) => {
              this.accounts = response.data
          })
        }
    },
    data() {
        return {
            showOverview: false,
            selectedAccount: {},
            accounts: [Object],
        }
    },
    created() {
        this.getAccounts()
        if (localStorage.selectedAccount){
            this.selectedAccount = JSON.parse(localStorage.selectedAccount)
        }
    },
    watch: {
        selectedAccount(account){
            if (JSON.stringify(account) !== undefined){
                localStorage.selectedAccount = JSON.stringify(account)
            }
        }
    }
}

</script>
