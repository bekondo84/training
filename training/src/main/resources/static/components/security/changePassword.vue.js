var changePwd = Vue.component("v-change-password", {
   props: [],
   data() {
      return {
         meta : null,
         data : {}
      }
   }, methods : {
       async confirm() {
         try {
               let response = await axios.post("/api/v1/changePassord", this.data);
               window.location.href = "/logout";
         } catch (error) {
             this.$emit("notify-error", error) ;
         }
       }
   }, computed : {
       oldPasswordLabel() {
          return this.meta != null ? this.meta.columns[0].label : null;
       }, newPasswordLabel() {
           return this.meta != null ? this.meta.columns[1].label : null;
       }, confirmPasswwordLabel() {
           return this.meta != null ? this.meta.columns[2].label : null;
       }, title() { return this.meta != null ? this.meta.formTitle : ""}
   }, async created() {
     try {
           let response = await axios.get("/api/v1/meta/cm.pak.training.beans.security.ChangePasswordData");
           this.meta = response.data ;
     } catch (error) {
         this.$emit("notify-error", error) ;
     }
   }, template: `<div class="col-md-6 offset-md-3">
                 	 <span class="anchor" id="formChangePassword"></span>
                 	 <hr class="mb-5">
                 	 <!-- form card change password -->
                 	 <div class="card card-outline-secondary">
                 		 <div class="card-header">
                 			 <h3 class="mb-0 title title">{{title}}</h3>
                 		 </div>
                 		 <div class="card-body">
                 		   <form class="form" role="form" autocomplete="off">
                 				 <div class="form-group">
                 					 <label for="inputPasswordNew" class="field">{{newPasswordLabel}}</label>
                 					 <input type="password" class="form-control form-control-sm"
                 							id="inputPasswordNew" required=""   v-model="data.newPassword">
                 					 <span class="form-text small text-muted">
                 							 The password must be 8-20 characters, and must <em>not</em> contain spaces.
                 						 </span>
                 				 </div>
                 				 <div class="form-group">
                 					 <label for="inputPasswordNewVerify"  class="field">{{confirmPasswwordLabel}}</label>
                 					 <input type="password" class="form-control form-control-sm"
                 							id="inputPasswordNewVerify" required=""   v-model="data.confirmPasswword">
                 					 <span class="form-text small text-muted">
                 							 To confirm, type the new password again.
                 						 </span>
                 				 </div>
                 				 <div class="form-group">
                 					 <button  class="btn btn-success btn-sm float-right" @click="confirm()">Save</button>
                 				 </div>
                 		   </form>
                 		 </div>
                 	 </div>
                  </div>`
});