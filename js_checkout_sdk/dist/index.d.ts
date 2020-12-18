interface Option {
    elementID: string;
    uuid: string;
    biller: string;
    to: string;
    cart?: string;
}
declare class SolusPayCheckout {
    Option: Option;
    constructor(option: Option);
    initPayButton(): void;
    pay(amount: number): void;
}
