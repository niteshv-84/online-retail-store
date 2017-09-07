INSERT INTO PRODUCT_CATEGORY (id , name , description , TAX_PERCENTAGE ) values(PRODUCT_CATEGORY_SEQ.nextVal , 'A' , 'Category A Products' , 10);

INSERT INTO PRODUCT  (id , name , description , rate, category_id  ) values(PRODUCT_SEQ.nextVal , 'Singapore Apple' , 'Singapore Export Quality Apple' , '100', PRODUCT_CATEGORY_SEQ.currVal );
INSERT INTO PRODUCT  (id , name , description , rate, category_id  ) values(PRODUCT_SEQ.nextVal , 'Indian Mango' , 'Indian High Quality Mango' , '80', PRODUCT_CATEGORY_SEQ.currVal );

INSERT INTO PRODUCT_CATEGORY (id , name , description , TAX_PERCENTAGE  ) values(PRODUCT_CATEGORY_SEQ.nextVal , 'B' , 'Category B Products' , '20');

INSERT INTO PRODUCT  (id , name , description , rate, category_id  ) values(PRODUCT_SEQ.nextVal , 'Biscuit' , 'Cream Filled Biscuit' , '75', PRODUCT_CATEGORY_SEQ.currVal );
INSERT INTO PRODUCT  (id , name , description , rate, category_id  ) values(PRODUCT_SEQ.nextVal , 'Chocolate' , 'Milk Chocolate' , '120', PRODUCT_CATEGORY_SEQ.currVal );

INSERT INTO PRODUCT_CATEGORY (id , name , description , TAX_PERCENTAGE  ) values(PRODUCT_CATEGORY_SEQ.nextVal , 'C' , 'Category C Products' , '0');

INSERT INTO PRODUCT  (id , name , description , rate, category_id  ) values(PRODUCT_SEQ.nextVal , 'Oil' , 'Refined Oil' , '150', PRODUCT_CATEGORY_SEQ.currVal );
INSERT INTO PRODUCT  (id , name , description , rate, category_id  ) values(PRODUCT_SEQ.nextVal , 'Milk' , 'Cows Milk' , '50', PRODUCT_CATEGORY_SEQ.currVal );