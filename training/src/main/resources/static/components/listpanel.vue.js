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
           },async processAction(action) {
                if (this.selectItem == null) {
                   alert("No row selected ! Please selected a row")
               }else {
                    var answer = confirm("Voulez vous coninuer ? ");
                    if (answer == false) {
                       return ;
                    }
                    if (action.method == "post") {
                       let response = await axios.post(this.menu.source.concat(action.source), this.selectItem);
                       var index = this.data.indexOf(this.selectItem);
                       this.data.splice(index, 1,response.data);
                    }
                    if (action.method == "get") {
                       this.$emit("process-action", {"data":Object.assign({}, this.data), "action":Object.assign({}, action)});
                    }
              }
       }
     },template: `<div class="editor">
                     <l-header :menu="menu"
                               :meta="meta"
                               :key="generateUniqueKey()"
                               :backbtn="backbtn"
                               @created-action="createdAction"
                               @process-action="processAction"
                               @cancel-event="onCancelEvent"/>
                     <l-table  :meta="meta"  :data="data" :key="generateUniqueKey2()"
                                @item-selected="itemSelected"/>
                  </div>`
});