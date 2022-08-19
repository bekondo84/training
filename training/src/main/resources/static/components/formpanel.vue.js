var formPanel = Vue.component("view-component", {
     props : ["menu", "data", "meta"],
     data() {
        return {

        }
     },computed: {

     }, methods : {
        onTabChangeEvent(event) {
          console.log("Tab has change")
        }
     },template : `<div class="editor"><f-header :meta="meta" :data="data" :menu="menu" /><f-body :meta="meta" :data="data" @change="onTabChangeEvent"/></div>`
});