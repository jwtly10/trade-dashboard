<template>
    <div id="login" class="d-flex justify-content-center text-center flex-column mt-5">
        <form onsubmit="return false;" class="m-auto w-100" method="post">
            <h3>Login</h3>
            <div class="form-group test m-2">
                <!--                <label for="exampleInputEmail1">Username</label>-->
                <input v-model="input.username" required type="text"
                       class="form-control login text-center border-0 text-white"
                       id="username" name="username"
                       placeholder="Username">
            </div>
            <div class="form-group m-2">
                <!--                <label for="exampleInputPassword1">Password</label>-->
                <input v-model="input.password" required type="password"
                       class="form-control text-center border-0 text-white"
                       id="password" name="password"
                       placeholder="Password">
            </div>
            <button type="button" v-on:click="login()" class="btn btn-primary">Login</button>
        </form>
        <p class="text-danger">{{ errorMsg }}</p>
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
                        // Create session cookie
                        let d = new Date();
                        d.setTime(d.getTime() + 60 * import.meta.env.VITE_SESSION_TIMEOUT * 1000);
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
        }
    }
}
</script>