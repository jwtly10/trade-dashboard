import './assets/main.css'
import {createApp} from 'vue'
import App from './App.vue'

// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import '../src/assets/custom.scss'

createApp(App).mount('#app')
