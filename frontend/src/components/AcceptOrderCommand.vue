<template>
    <v-card outlined>
        <v-card-title>
            AcceptOrder
        </v-card-title>

        <v-card-text>
            <String label="DeliveryId" v-model="value.deliveryId" :editMode="editMode"/>
            <String label="OrderId" v-model="value.orderId" :editMode="editMode"/>
            <String label="DriverId" v-model="value.driverId" :editMode="editMode"/>
            <Address offline label="Address" v-model="value.address" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="acceptOrder"
            >
                AcceptOrder
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'AcceptOrderCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.deliveryId = '';
            this.value.orderId = '';
            this.value.driverId = '';
            this.value.address = {};
        },
        watch: {
        },
        methods: {
            acceptOrder() {
                this.$emit('acceptOrder', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

