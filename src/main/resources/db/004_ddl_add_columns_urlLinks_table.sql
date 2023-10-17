ALTER TABLE url_links
ADD COLUMN calls   int default 0,
ADD COLUMN site_id int references sites(id);