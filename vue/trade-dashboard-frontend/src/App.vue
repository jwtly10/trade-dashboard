<template>
    <div class="text-center" id="app">
        <nav class="navbar p-3">
            <div class="container">
                <span class="h4 mb-0 h1">trade-dashboard-frontend</span>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" v-model="showOverview" value="" id="showOverViewCB">
                    <label class="form-check-label" for="showOverViewCB">Show Overview</label>
                </div>

            </div>
        </nav>
        <div class="container">
            <div v-if="showOverview">
                <Overview/>
            </div>
            <div v-else class="content-wrapper pt-4 text-center">
                <div class="row d-flex justify-content-sm-end">
                    <div class="col-lg-3">
                        <AccountPicker class="mb-4 justify-content-end" v-if="!showOverview" :accounts="accounts"
                            :account="selectedAccount" v-model="selectedAccount" />

                    </div>
                </div>
                <div class="row d-flex justify-content-center">
                    <BaseStats v-bind:account="selectedAccount" />
                    <div class="row">
                        <Trades />
                    </div>
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
import Overview from "@/components/Overview.vue"

export default {
    name: 'App',
    components: {
        BaseStats,
        Trades,
        AccountPicker,
        Overview
    },
    methods: {
        async getAccounts() {
            await AccountService.getAccounts().then((response) => {
                this.accounts = response.data
            })
        }
    },
    data() {
        return {
            showOverview: true,
            selectedAccount: {},
            accounts: [Object],
        }
    },
    created() {
        this.getAccounts()
        if (localStorage.selectedAccount) {
            this.selectedAccount = JSON.parse(localStorage.selectedAccount)
        }

        if (localStorage.showOverview) {
            this.showOverview = localStorage.showOverview === 'true'
        }
    },
    watch: {
        showOverview() {
            localStorage.showOverview = this.showOverview
        },
        selectedAccount(account) {
            if (JSON.stringify(account) !== undefined) {
                localStorage.selectedAccount = JSON.stringify(account)
            }
        }
    }
}

</script>
