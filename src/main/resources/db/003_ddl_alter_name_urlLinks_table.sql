ALTER TABLE urlLinks
RENAME TO url_links;
ALTER TABLE url_links
RENAME COLUMN nameOri TO name_ori;
ALTER TABLE url_links
RENAME COLUMN nameMod TO name_mod;

