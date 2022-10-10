var listComponent = Vue.component("list-component", {
     props : ["menu", "data", "meta", "backbtn"],
     data() {
        return {
           welcome : "Form list",
           selectItem : null
        }
     },methods : {
          generateUniqueKey() {
                        return "h"+Date.now().toString();
                       },
           generateUniqueKey2() {
                                   return "l"+Date.now().toString();
                              },
          itemSelected(item) {
                                 if (item.viewMode == "list") {
                                     this.selectItem = item.item;
                                 } else {
                                       this.$emit("item-selected", {item :item, viewMode:"view"});
                                 }
                             },
          createdAction (item) {
              this.$emit("created-action", item);
          },onCancelEvent() {
             this.$emit("cancel-event");
           }, notifyError(error) {
               this.$emit("notify-error", error)
           }, notifySuccess() {
                           this.$emit("notify-success")
           },async processAction(action) {
             try{
               var answer = confirm("Voulez vous coninuer ? ");
               if (answer == false) {
                  return ;
               }
               if (action.method == "post") {
                  if (this.selectItem == null) {
                     alert("No row selected\n Please selected a row and try again");
                  } else {
                      let response = await axios.post(action.source, this.selectItem);
                      var index = this.data.indexOf(this.selectItem);
                      this.data.splice(index, 1,response.data);
                      this.notifySuccess();
                  }
               }
               if (action.method == "get") {
                  if (this.selectItem == null) {
                      alert("No row selected\n Please selected a row and try again");
                  } else {
                      let response = await axios.get(action.source.concat("/").concat(this.selectItem.pk));
                      let obj = response.data ;
                      this.$emit("process-action", {"data":obj, "action":Object.assign({}, action)});
                  }
               }
           } catch (error) {
                this.notifyError(error);
           }
       },searchAction(text) {
          this.$emit("search-action", text);
      }
     },template: `<div class="editor">
                     <l-header :menu="menu"
                               :meta="meta"
                               :key="generateUniqueKey()"
                               :backbtn="backbtn"
                               @created-action="createdAction"
                               @process-action="processAction"
                               @cancel-event="onCancelEvent"
                               @notify-error="notifyError"
                               @search-action="searchAction"/>
                     <l-table  :meta="meta"  :data="data" :key="generateUniqueKey2()"
                                @item-selected="itemSelected"/>
                  </div>`
});