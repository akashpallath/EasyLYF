    package com.example.easylyf.models;

    public class ModelProduct {
        private String productID, productTitle, productDescription, productCategory, productQuantity,
                productPrice, productDiscountedPrice, productDiscountedNote, productPic, discountAvailable, timestamp, uid;

        public ModelProduct() {

        }

        public ModelProduct(String productID, String productTitle, String productDescription, String productCategory,
                            String productQuantity, String productPrice, String productDiscountedPrice, String productDiscountedNote,
                            String productPic, String discountAvailable, String timestamp, String uid) {
            this.productID = productID;
            this.productTitle = productTitle;
            this.productDescription = productDescription;
            this.productCategory = productCategory;
            this.productQuantity = productQuantity;
            this.productPrice = productPrice;
            this.productDiscountedPrice = productDiscountedPrice;
            this.productDiscountedNote = productDiscountedNote;
            this.productPic = productPic;
            this.discountAvailable = discountAvailable;
            this.timestamp = timestamp;
            this.uid = uid;
        }

        public String getProductID() {
            return productID;
        }

        public void setProductID(String productID) {
            this.productID = productID;
        }

        public String getProductTitle() {
            return productTitle;
        }

        public void setProductTitle(String productTitle) {
            this.productTitle = productTitle;
        }

        public String getProductDescription() {
            return productDescription;
        }

        public void setProductDescription(String productDescription) {
            this.productDescription = productDescription;
        }

        public String getProductCategory() {
            return productCategory;
        }

        public void setProductCategory(String productCategory) {
            this.productCategory = productCategory;
        }

        public String getProductQuantity() {
            return productQuantity;
        }

        public void setProductQuantity(String productQuantity) {
            this.productQuantity = productQuantity;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductDiscountedPrice() {
            return productDiscountedPrice;
        }

        public void setProductDiscountedPrice(String productDiscountedPrice) {
            this.productDiscountedPrice = productDiscountedPrice;
        }

        public String getProductDiscountedNote() {
            return productDiscountedNote;
        }

        public void setProductDiscountedNote(String productDiscountedNote) {
            this.productDiscountedNote = productDiscountedNote;
        }

        public String getProductPic() {
            return productPic;
        }

        public void setProductPic(String productPic) {
            this.productPic = productPic;
        }

        public String getDiscountAvailable() {
            return discountAvailable;
        }

        public void setDiscountAvailable(String discountAvailable) {
            this.discountAvailable = discountAvailable;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
