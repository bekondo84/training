var formPanel = Vue.component("view-component", {
     props : ["menu", "data", "meta"],
     data() {
        return {

        }
     },computed: {

     }, methods : {
        onTabChangeEvent(event) {
          console.log("Tab has change")
        },
        onCancelEvent() {
          this.$emit("form-cancel-event");
        },refreshListForm() {
           this.$emit("refresh-list-form")
        }
     },template : `<div class="editor">
                      <f-header :meta="meta"
                                :data="data"
                                :menu="menu"
                                @form-cancel-event="onCancelEvent"
                                @refresh-list-form="refreshListForm"/>
                      <f-body :meta="meta"
                              :data="data"
                              @change="onTabChangeEvent"/>
                   </div>`
});