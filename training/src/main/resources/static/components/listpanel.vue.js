var listComponent = Vue.component("list-component", {
     props : ["menu", "data", "meta", "backbtn"],
     data() {
        return {
           welcome : "Form list",
           selectItem : null,
           i18n: {}
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
               if (action.method == "post") {
                  if (this.selectItem == null) {
                     alert(this.getMessage('norowselect.alert'));
                  } else {
                     if (confirm(this.getMessage('confirm.alert'))){
                          let response = await axios.post(action.source, this.selectItem);
                          var index = this.data.indexOf(this.selectItem);
                          this.data.splice(index, 1,response.data);
                          this.notifySuccess();
                      }
                  }
               }
               if (action.method == "get") {
                  if (this.selectItem == null) {
                      alert(this.getMessage('norowselect.alert'));
                  } else {
                     if (confirm(this.getMessage('confirm.alert'))) {
                          let response = await axios.get(action.source.concat("/").concat(this.selectItem.pk));
                          let obj = response.data ;
                          this.$emit("process-action", {"data":obj, "action":Object.assign({}, action)});
                      }
                  }
               }
           } catch (error) {
                this.notifyError(error);
           }
       },searchAction(text) {
          this.$emit("search-action", text);
      },getMessage(key) {
          return this.i18n!= null && this.i18n[key]!=null ? this.i18n[key]: key;
       }
     }, async created() {
        try {
             let response = await axios.get("/api/v1/i18n?keys=confirm.alert,norowselect.alert,confirm.alert");
             this.i18n = response.data;
         } catch(error) {
            console.log(error);
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