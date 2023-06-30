<template>
    <select class="form-select w-50 search border-0 bg-dark"
            @input="changeAccount()"
            v-model="selectedAccount">
        <option v-for="account in accounts" :key="account.accountID"
                v-bind:value="account">
            {{ account.accountID !== undefined ? account.accountID + " - " + account.accountSize / 1000 + "K " + account.accountType : 'Fetching accounts failed'}}
        </option>
    </select>
</template>

<script>
export default {
    name: 'AccountPicker',
    props: {
        accounts : [Object],
        account : Object
    },
    methods: {
        changeAccount() {
            this.$emit('input', this.selectedAccount);
        }
    },
    data() {
        return {
            selectedAccount: this.account
        }
    }}
</script>


