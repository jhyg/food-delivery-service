
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import CustomerOrderManagementOrderManager from "./components/listers/CustomerOrderManagementOrderCards"
import CustomerOrderManagementOrderDetail from "./components/listers/CustomerOrderManagementOrderDetail"

import MenuManagementMenuManager from "./components/listers/MenuManagementMenuCards"
import MenuManagementMenuDetail from "./components/listers/MenuManagementMenuDetail"

import DeliveryManagementDeliveryManager from "./components/listers/DeliveryManagementDeliveryCards"
import DeliveryManagementDeliveryDetail from "./components/listers/DeliveryManagementDeliveryDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/customerOrderManagements/orders',
                name: 'CustomerOrderManagementOrderManager',
                component: CustomerOrderManagementOrderManager
            },
            {
                path: '/customerOrderManagements/orders/:id',
                name: 'CustomerOrderManagementOrderDetail',
                component: CustomerOrderManagementOrderDetail
            },

            {
                path: '/menuManagements/menus',
                name: 'MenuManagementMenuManager',
                component: MenuManagementMenuManager
            },
            {
                path: '/menuManagements/menus/:id',
                name: 'MenuManagementMenuDetail',
                component: MenuManagementMenuDetail
            },

            {
                path: '/deliveryManagements/deliveries',
                name: 'DeliveryManagementDeliveryManager',
                component: DeliveryManagementDeliveryManager
            },
            {
                path: '/deliveryManagements/deliveries/:id',
                name: 'DeliveryManagementDeliveryDetail',
                component: DeliveryManagementDeliveryDetail
            },



    ]
})
