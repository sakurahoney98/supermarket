PGDMP      4                |            teste    16.1    16.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    155682    teste    DATABASE     |   CREATE DATABASE teste WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE teste;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    155690    category    TABLE     o   CREATE TABLE public.category (
    id_category integer NOT NULL,
    description character varying NOT NULL
);
    DROP TABLE public.category;
       public         heap    postgres    false    4            �            1259    155695    product    TABLE     A  CREATE TABLE public.product (
    id_product integer NOT NULL,
    name_product character varying NOT NULL,
    duration integer NOT NULL,
    stock_quantity integer NOT NULL,
    purchase_date date NOT NULL,
    category integer NOT NULL,
    max_purchase integer,
    kg_unity character varying,
    confirm boolean
);
    DROP TABLE public.product;
       public         heap    postgres    false    4            �            1259    155700    all_products    VIEW     }  CREATE VIEW public.all_products AS
 SELECT product.id_product,
    product.name_product,
    product.kg_unity,
    product.duration,
    product.stock_quantity,
    product.purchase_date,
    product.category,
    category.description,
    product.max_purchase,
    product.confirm
   FROM (public.product
     JOIN public.category ON ((product.category = category.id_category)));
    DROP VIEW public.all_products;
       public          postgres    false    216    216    216    216    216    216    216    216    215    215    216    4            �          0    155690    category 
   TABLE DATA           <   COPY public.category (id_category, description) FROM stdin;
    public          postgres    false    215   �       �          0    155695    product 
   TABLE DATA           �   COPY public.product (id_product, name_product, duration, stock_quantity, purchase_date, category, max_purchase, kg_unity, confirm) FROM stdin;
    public          postgres    false    216          "           2606    155705    category categoria_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.category
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id_category);
 A   ALTER TABLE ONLY public.category DROP CONSTRAINT categoria_pkey;
       public            postgres    false    215            #           2606    155706    product produto_categoria    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT produto_categoria FOREIGN KEY (category) REFERENCES public.category(id_category);
 C   ALTER TABLE ONLY public.product DROP CONSTRAINT produto_categoria;
       public          postgres    false    4642    216    215            �   K   x�3����-H�J�2���L�L�KUH-.�O��2�t+��/�2�t���LI�2�t�()J�2
Te�e$r��qqq ���      �      x������ � �     