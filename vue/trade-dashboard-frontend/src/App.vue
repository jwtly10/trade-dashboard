<template>
    <div class="text-center" id="app">
        <nav class="navbar p-3">
            <div class="container d-flex align-items-center flex-row">
                <span class="h4 mb-0 h1">trade-dashboard-frontend</span>
                <!--                <div class="form-check">-->
                <!--                    <input class="form-check-input" type="checkbox" v-model="showOverview" value="" id="showOverViewCB">-->
                <!--                    <label class="form-check-label" for="showOverViewCB">Show Overview</label>-->
                <!--                </div>-->
                <img v-if="loggedIn" v-on:click="logout" class="p-0 m-0 logout" src="/logout.png" alt="logout-button">
            </div>
        </nav>
        <div class="container">
            <Login v-if="!loggedIn"
                   @authenticated="loginAuthenticatedUser"/>
            <div v-else>
                <Overview/>
            </div>
            <!--            <div v-if="showOverview">-->
            <!--                <Overview/>-->
            <!--            </div>-->
            <!--            <div v-else class="content-wrapper pt-4 text-center">-->
            <!--                <div class="row d-flex justify-content-sm-end">-->
            <!--                    <div class="col-lg-3">-->
            <!--                        <AccountPicker class="mb-4 justify-content-end" v-if="!showOverview" :accounts="accounts"-->
            <!--                                       :account="selectedAccount" v-model="selectedAccount"/>-->

            <!--                    </div>-->
            <!--                </div>-->
            <!--                <div class="row d-flex justify-content-center">-->
            <!--                    <p v-if="!showBase">Please select an account</p>-->
            <!--                    <BaseStats v-else v-bind:account="selectedAccount"/>-->
            <!--                </div>-->
            <!--            </div>-->
        </div>
    </div>
</template>

<script>
import Trades from './components/Trades.vue'
import BaseStats from "@/components/BaseStats.vue";
import AccountPicker from "@/components/AccountPicker.vue";
import AccountService from "@/services/accountService";
import Overview from "@/components/Overview.vue"
import Login from "@/components/Login.vue"

export default {
    name: 'App',
    components: {
        BaseStats,
        Trades,
        AccountPicker,
        Overview,
        Login
    },
    methods: {
        async getAccounts() {
            await AccountService.getAccounts().then((response) => {
                this.accounts = response.data
            })
        },
        loginAuthenticatedUser() {
            this.loggedIn = true;
        },
        getCookie(name) {
            const value = `; ${document.cookie}`;
            const parts = value.split(`; ${name}=`);
            if (parts.length === 2) return parts.pop().split(';').shift();
        },
        logout() {
            this.loggedIn = false;
            document.cookie = 'Token' + '=; Max-Age=0'
        }
    },
    data() {
        return {
            showOverview: true,
            loggedIn: false,
            showBase: false,
            selectedAccount: {},
            accounts: [Object],
        }
    },
    created() {
        // this.getAccounts()
        if (localStorage.selectedAccount) {
            this.selectedAccount = JSON.parse(localStorage.selectedAccount)
        }

        if (localStorage.showOverview) {
            this.showOverview = localStorage.showOverview === 'true'
        }

        // Check if token valid
        if (this.getCookie("Token")) {
            this.loggedIn = true
        }
    },
    watch: {
        showOverview() {
            localStorage.showOverview = this.showOverview
        },
        selectedAccount(account) {
            if (JSON.stringify(account) !== undefined) {
                this.showBase = true
                localStorage.selectedAccount = JSON.stringify(account)
            }
        },
        loggedIn() {
            if (this.loggedIn) {
                this.getAccounts()
            }
        }
    }
}

</script>
