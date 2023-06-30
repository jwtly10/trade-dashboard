<template>
    <div class="card">
        <div class="card-body p-4">
            <div class="d-flex flex-row justify-content-between">
                <div>
                    <h4 class="card-title pb-4">Accounts Overview</h4>
                </div>
            </div>
            <div class="row">
                <OverviewItem />
            </div>
        </div>
    </div>

</template>

<script>
import OverviewItem from "@/components/OverviewItem.vue";

export default {
    name: 'OverviewList',
    components: {OverviewItem},
    props: {

    },
    methods:{

    }
}
</script>


