insert into users values (
  'sasuke'
);

insert into forums values (
  default,
  'sasuke',
  'revenge',
  'looking for for friends to help me get revenge',
  ARRAY['revenge', 'friends']
);


insert into posts values (
  default,
  'sasuke',
  'need help funding my revenge',
  'same as title',
  ARRAY['revenge'],
  ARRAY['kdlfklsdf'],
  default,
  default
);
