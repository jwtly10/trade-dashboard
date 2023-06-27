<template>
    <div class="card d-inline-flex flex-lg-row flex-column bg-dark w-100 mb-3">
        <div v-for="stats in data" class="card-body">
            <div class="d-flex justify-content-center align-items-center align-self-start">
                <h3 class="money-card-title mb-0">{{stats.value}}</h3>
            </div>
            <p class="money-card-text">{{stats.title}}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Stat',
    props: {
        data : [Object]
    }
}
</script>


