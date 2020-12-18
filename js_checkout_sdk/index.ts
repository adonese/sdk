interface Option {
  elementID : string;
  uuid : string;
  biller : string;
  to: string
  cart? : string
}
// eslint-disable-next-line no-unused-vars
class SolusPayCheckout {
  Option : Option = {
    elementID: 'pay_button', uuid: '', biller: '', to: '',
  }

  constructor(option : Option) {
    this.Option.elementID = option.elementID;
    this.Option.biller = option.biller;
    this.Option.to = option.to;
    fetch('https://beta.soluspay.net/api/v1/payment_token/', { method: 'POST' }).then((data) => data.json().then((res) => { this.Option.uuid = res.uuid; })).catch((e) => console.log(e));
  }

  initPayButton() {
    document.getElementById(this.Option.elementID)!.innerHTML += '<div style="background-color: #E95363;padding-left: .5rem ;padding-right: .5rem; border-radius: 8px; width: 200px; height: 40px; border-color: gray; border-width: .1rem; color: white; font-weight: bold; font-size: large;display: flex; justify-content: space-between ; align-items: center ;  text-align: center;"><svg width="24.418" height="15.659" viewBox="0 0 24.418 15.659"><g id="Group_133" data-name="Group 133" transform="translate(-7690.598 -3964.665)"><path id="Path_62" data-name="Path 62" d="M127.514,95.694V93.177h22.166c0-.581.005-1.137-.008-1.692,0-.058-.1-.133-.171-.166a.573.573,0,0,0-.221-.008h-22.8V90.1h23.029a1.362,1.362,0,0,1,1.387,1.382q0,6.438,0,12.875a1.376,1.376,0,0,1-1.4,1.4q-9.166,0-18.332,0c-.051,0-.1-.005-.169-.009v-1.215h18.274c.074,0,.163.023.22-.008a.7.7,0,0,0,.192-.2c.018-.023,0-.072,0-.109V95.694Z" transform="translate(7564.12 3874.565)" fill="#fff"/><path id="Path_68" data-name="Path 68" d="M215.6,136.794c-.528,0-1.056,0-1.584,0a.844.844,0,0,1-.879-.844c-.011-.513-.01-1.026,0-1.538a.831.831,0,0,1,.827-.851q1.651-.022,3.3,0a.834.834,0,0,1,.812.844q.016.769,0,1.538a.844.844,0,0,1-.894.852C216.652,136.795,216.124,136.794,215.6,136.794Z" transform="translate(7493.609 3839.207)" fill="#fff"/><path id="Path_71" data-name="Path 71" d="M144.705,149.083h5.8v1.453h-5.8Z" transform="translate(7549.288 3826.566)" fill="#fff"/><path id="Path_72" data-name="Path 72" d="M144.345,133.041h-5.176V131.59h5.176Z" transform="translate(7553.792 3840.802)" fill="#fff"/></g></svg>Pay<div class=""/></div>';
  }

  pay(amount : number) {
    window.location.replace(`http://pay.noebs.dev/?biller=${this.Option.biller}&id=${this.Option.cart}&token=${this.Option.uuid}&to=${this.Option.to}&amount=${amount}`);
  }
}
