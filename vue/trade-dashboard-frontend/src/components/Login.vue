<template>
    <div id="login" class="d-flex justify-content-center flex-column">
        <form onsubmit="return false;" method="post">
            <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <input v-model="input.username" required type="text" class="form-control border-0 text-white"
                       id="username" name="username"
                       placeholder="Enter username">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input v-model="input.password" required type="password" class="form-control border-0 text-white"
                       id="password" name="password"
                       placeholder="Password">
            </div>
            <button type="button" v-on:click="login()" class="btn btn-primary">Login</button>
        </form>
        <p class="text-danger">{{ errorMsg }}</p>

        <!--        <h1>Login</h1>
                <div class="form-inputs">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" v-model="input.username" placeholder="Username"/>
                </div>
                <div class="form-inputs">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" v-model="input.password" placeholder="Password"/>
                </div>
                <button type="button" v-on:click="login()">Login</button>-->
    </div>
</template>

<script>
import AuthenticationService from "@/services/authenticationService";

export default {
    name: 'Login',
    data() {
        return {
            input: {
                username: "",
                password: ""
            },
            errorMsg: "",
            authenticated: false
        }
    },
    methods: {
        login() {
            if (this.input.username !== "" && this.input.password !== "") {
                AuthenticationService.authenticate(this.input.username, this.input.password).then((response) => {
                    if (response.status === 200) {
                        this.errorMsg = "Auth Success"
                        console.log("Token: " + response.data.token)
                        let d = new Date();
                        d.setTime(d.getTime() + 24 * 60 * 60 * 1000);
                        let expires = "expires=" + d.toUTCString();
                        document.cookie =
                            "Token=" + response.data.token + ";" + expires + ";path=/";
                        // Now logged in
                        this.authenticated = true
                        this.$emit("authenticated", true);
                    } else {
                        this.errorMsg = "Authentication Failed"
                    }
                })
            } else {
                this.errorMsg = "A username/email and password must be present"
            }
            /*if (this.input.username !== "" && this.input.password !== "") {
                authenticate(this.input.username, this.input.password)
                // This should actually be an api call not a check against this.$parent.mockAccount
                // if (this.input.username === this.$parent.mockAccount.username && this.input.password == this.$parent.mockAccount.password) {
                //     this.$emit("authenticated", true);
                //     this.$router.replace({name: "Secure"});
                // } else {
                //     console.log("The username and / or password is incorrect");
                // }
            } else {
                console.log("A username and password must be present");
            }*/
        }
    }
}
</script>