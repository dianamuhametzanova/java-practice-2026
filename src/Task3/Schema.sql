create table product (
    id serial unique not null,
    name char(20) not null,
    price integer check (price >= 0)
);
select * from product;