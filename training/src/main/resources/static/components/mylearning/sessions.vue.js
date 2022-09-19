var session = Vue.component("session", {
   props : ["data", "meta", "menu"],
   data() {
      return {
        container : "s-body",
        caretaker : [],
        item : {}
      }
   }, computed : {
      dialogId() { return "my-dialog"; }
   }, methods : {
     async register(item) {
        let response = await axios.get("/api/v1/mylearning/".concat(item.pk));
        this.item = response.data;
        var modal = new bootstrap.Modal(document.getElementById(this.dialogId), {
                          keyboard: false
                        });
                        modal.show();
     }, async registerGroup(group) {

     }
   }, created() {

   }, template : `<div class="editor">
        <my-dialog
             :data="item"
             @register-action="registerGroup"></my-dialog>
         <l-header
             :data="data"
             :meta="meta"
             :menu="menu"
           ></l-header>
           <s-body
             :meta="meta"
             :data="data"
             @register-action="register">
           </s-body>
   </div>`
});