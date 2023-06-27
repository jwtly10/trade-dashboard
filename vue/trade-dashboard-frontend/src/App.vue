<template>
    <div class="text-center" id="app">
        <div class="container text-center">
            <div class="row">
                <BaseStats v-bind="account"/>
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

export default {
    name: 'App',
    components: {
        BaseStats,
        Trades
    },
    data() {
        return {
            account: {
                accountID: 10155469,
                accountKey: "26f45d70-73f4-447f-9cd6-6b798246cbf6",
                accountSize: 10000,
                accountType: "LIVE",
                created: "2023-06-27T16:09:51.000+00:00"
            },
        }
    }
}

</script>

