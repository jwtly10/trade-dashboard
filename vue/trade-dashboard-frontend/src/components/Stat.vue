<template>
    <div class="card d-inline-flex flex-lg-row bg-dark w-100 mb-3">
        <div v-for="stats in data" class="card-body">
            <div class="d-flex justify-content-left align-items-center align-self-start">
                <h3 class="money-card-title mb-0">{{stats.value || 'Loading ...'}}</h3>
            </div>
            <p class="money-card-text text-start">{{stats.title}}</p>
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


