<template>
    <v-card outlined>
        <v-card-title>
            RegisterMenu
        </v-card-title>

        <v-card-text>
            <String label="MenuId" v-model="value.menuId" :editMode="editMode"/>
            <String label="Name" v-model="value.name" :editMode="editMode"/>
            <Money offline label="Price" v-model="value.price" :editMode="editMode" @change="change"/>
            <String label="Description" v-model="value.description" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="registerMenu"
            >
                RegisterMenu
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
        name: 'RegisterMenuCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.menuId = '';
            this.value.name = '';
            this.value.price = {};
            this.value.description = '';
        },
        watch: {
        },
        methods: {
            registerMenu() {
                this.$emit('registerMenu', this.value);
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

