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
          this.$emit("cancel-event");
        },refreshListForm() {
           this.$emit("refresh-list-form")
        },async processAction(action) {
           try{
             var answer = confirm("Voulez vous coninuer ? ");
             if (answer == false) {
                 return ;
             }
             if (action.method == "post") {
                 let response = await axios.post(action.source, this.data);
                 Object.assign(this.data, response.data);
             }
             if (action.method == "get") {
                 this.$emit("process-action", {"data":Object.assign({}, this.data), "action":Object.assign({}, action)});
             }
           } catch (error) {
                this.notifyError(error) ;
           }
         }, notifySuccess() {
             this.$emit("notify-success")
         }, notifyError(error) {
               this.$emit("notify-success", error)
           }
     },created(){
        //console.log("view-component created ************** : "+JSON.stringify(this.meta))
     },template : `<div class="editor">
                      <f-header :meta="meta"
                                :data="data"
                                :menu="menu"
                                @cancel-event="onCancelEvent"
                                @refresh-list-form="refreshListForm"
                                @process-action="processAction"
                                @notify-success="notifySuccess"
                                @notify-error="notifyError"/>
                      <f-body :meta="meta"
                              :data="data"
                              @change="onTabChangeEvent"
                              @process-action="processAction"/>
                   </div>`
});