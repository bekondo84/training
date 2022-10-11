var session = Vue.component("session", {
   props : ["data", "meta", "menu"],
   data() {
      return {
        localdata : Object.assign({}, this.data),
        container : "s-body",
        item : {},
        selectedGroup : {},
        i18n: {}
      }
   }, computed : {
      dialogId() { return "my-dialog"; }
   }, methods : {
     notifyError(error) {
        this.$emit("notify-error", error);
     },async register(item) {
        var modal = new bootstrap.Modal(document.getElementById(this.dialogId), {
                          keyboard: false
                        });
        modal.show();
        let response = await axios.get("/api/v1/mylearning/".concat(item.pk));
        this.item = response.data;
     }, async registerGroup(group) {
        try {
           let response = await axios.post("/api/v1/mylearning/register/".concat(group.pk), this.item);
           this.localdata = Object.assign({}, response.data);
           var modal = bootstrap.Modal.getInstance(document.getElementById(this.dialogId));
            modal.hide();
         } catch (error) {
             this.notifyError(error);
         }
     }, async unregister(item) {
          try {
             var notify = confirm(this.getMessage('unregister.alert'));
             if (notify) {
                 let response = await axios.post("/api/v1/mylearning/unregister/", item);
                 this.localdata = Object.assign({}, response.data);
             }
           } catch (error) {
               this.notifyError(error);
           }
     }, searchAction(text) {
        console.log("------------:Search Text : "+text)
     },getMessage(key) {
       return this.i18n!= null && this.i18n[key]!=null ? this.i18n[key]: key;
     }
   }, watch: {
      data: function(newVal, oldVal) {
           this.localdata = Object.assign({}, newVal);
      },
   }, async created() {
      try {
           let response = await axios.get("/api/v1/i18n?keys=unregister.alert");
           this.i18n = response.data;
       } catch(error) {
          this.notifyError(error);
       }
   }, template : `<div class="editor">
         <l-header
             :data="localdata"
             :meta="meta"
             :menu="menu"
             @search-action="searchAction"
           ></l-header>
           <s-body
             :meta="meta"
             :data="localdata"
             @register-action="register"
             @unregister-action="unregister">
           </s-body>
           <myDialog
                :data="item"
                @register-action="registerGroup"></myDialog>
   </div>`
});