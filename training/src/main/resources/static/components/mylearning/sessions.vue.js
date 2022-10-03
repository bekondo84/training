var session = Vue.component("session", {
   props : ["data", "meta", "menu"],
   data() {
      return {
        localdata : Object.assign({}, this.data),
        container : "s-body",
        item : {},
        selectedGroup : {}
      }
   }, computed : {
      dialogId() { return "my-dialog"; }
   }, methods : {
     async register(item) {
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
             console.log(error);
         }
     }, async unRegisterGroup(group) {
          try {
             let response = await axios.post("/api/v1/mylearning/unregister/".concat(group.pk), this.item);
             this.localdata = Object.assign({}, response.data);
             var modal = bootstrap.Modal.getInstance(document.getElementById(this.dialogId));
              modal.hide();
           } catch (error) {
               console.log(error);
           }
     }
   }, watch: {
      data: function(newVal, oldVal) {
           this.localdata = Object.assign({}, newVal);
      },
   }, created() {

   }, template : `<div class="editor">
         <l-header
             :data="localdata"
             :meta="meta"
             :menu="menu"
           ></l-header>
           <s-body
             :meta="meta"
             :data="localdata"
             @register-action="register">
           </s-body>
           <myDialog
                :data="item"
                @register-action="registerGroup"
                @unregister-action="unRegisterGroup"></myDialog>
   </div>`
});