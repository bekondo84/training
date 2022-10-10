Vue.use(VueI18n);
const messages = {
   fr: {
      cancel: {
         btn: 'Fermer'
      }
   },en: {
      cancel: {
           btn: 'Close'
        }
   }
}

const i18n = new VueI18n({
   locale: 'fr',
   messages
})