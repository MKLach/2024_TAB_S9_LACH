IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'sopkom')
BEGIN
	
	CREATE DATABASE sopkom
	COLLATE Polish_CS_AS
END
GO
USE [sopkom]
GO
/****** Object:  Table [dbo].[autobusy]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[autobusy](
	[autbous_id] [bigint] IDENTITY(1,1) NOT NULL,
	[numer_rejestracyjny] [varchar](10) NOT NULL,
	[przegladwaznydo] [date] NOT NULL,
	[status] [varchar](20) NOT NULL,
	[przebieg] [real] NOT NULL,
 CONSTRAINT [PK_autobusy] PRIMARY KEY CLUSTERED 
(
	[autbous_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[bilety]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bilety](
	[bilet_id] [tinyint] NOT NULL,
	[cena] [real] NOT NULL,
	[nazwa] [varchar](30) NOT NULL,
 CONSTRAINT [PK_bilety] PRIMARY KEY CLUSTERED 
(
	[bilet_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[harmonogramy]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[harmonogramy](
	[hamonogram_id] [smallint] IDENTITY(1,1) NOT NULL,
	[nazwa] [varchar](50) NULL,
	[pon] [bit] NOT NULL,
	[wto] [bit] NOT NULL,
	[sro] [bit] NOT NULL,
	[czw] [bit] NOT NULL,
	[pia] [bit] NOT NULL,
	[sob] [bit] NOT NULL,
	[nie] [bit] NOT NULL,
	[dodatkowe_inf] [varchar](750) NOT NULL,
 CONSTRAINT [PK_harmonogramy] PRIMARY KEY CLUSTERED 
(
	[hamonogram_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[incydenty]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[incydenty](
	[incydent_id] [bigint] IDENTITY(1,1) NOT NULL,
	[typ] [varchar](50) NULL,
	[date] [date] NULL,
	[autobus_id] [bigint] NULL,
	[kierowca_id] [bigint] NULL,
	[przejazd_id] [bigint] NULL,
	[dodatkowe_informacje] [varchar](800) NOT NULL,
	[koszty] [real] NULL,
	[krotko] [varchar](60) NOT NULL,
 CONSTRAINT [PK_incydenty] PRIMARY KEY CLUSTERED 
(
	[incydent_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[kierowcy]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[kierowcy](
	[kierowca_id] [bigint] IDENTITY(1,1) NOT NULL,
	[prawojazdywaznedo] [date] NOT NULL,
	[imie] [varchar](30) NOT NULL,
	[nazwisko] [varchar](30) NOT NULL,
	[pesel] [varchar](30) NOT NULL,
	[user_id] [bigint] NULL,
 CONSTRAINT [PK_kierowcy] PRIMARY KEY CLUSTERED 
(
	[kierowca_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[kursprzystanekwlini]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[kursprzystanekwlini](
	[kursprzystankiwlini_id] [bigint] IDENTITY(1,1) NOT NULL,
	[kurs_id] [bigint] NOT NULL,
	[przystanekwlini_id] [bigint] NOT NULL,
	[godzinna] [time](7) NOT NULL,
 CONSTRAINT [PK_kursprzystanekwlini] PRIMARY KEY CLUSTERED 
(
	[kursprzystankiwlini_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[kursy]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[kursy](
	[kurs_id] [bigint] IDENTITY(1,1) NOT NULL,
	[linia_id] [bigint] NOT NULL,
	[harmonogram_id] [smallint] NOT NULL,
	[kierunek] [smallint] NOT NULL,
	[typ_autobusu] [smallint] NOT NULL,
 CONSTRAINT [PK_kursy] PRIMARY KEY CLUSTERED 
(
	[kurs_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[linie]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[linie](
	[linia_id] [bigint] IDENTITY(1,1) NOT NULL,
	[numer] [varchar](50) NULL,
 CONSTRAINT [PK_linie] PRIMARY KEY CLUSTERED 
(
	[linia_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[przejazdbilet]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[przejazdbilet](
	[przejazdbilet_id] [bigint] IDENTITY(1,1) NOT NULL,
	[przejazd_id] [bigint] NOT NULL,
	[cena_biletu] [real] NOT NULL,
	[bilet_id] [tinyint] NOT NULL,
 CONSTRAINT [PK_przejazdbilet] PRIMARY KEY CLUSTERED 
(
	[przejazdbilet_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[przejazdkursprzystanekwlini]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[przejazdkursprzystanekwlini](
	[przejazdkursprzystanekwlini_id] [bigint] IDENTITY(1,1) NOT NULL,
	[przejazd_id] [bigint] NOT NULL,
	[kursprzystanekwlini_id] [bigint] NOT NULL,
	[realnagodzinna] [datetime] NULL,
 CONSTRAINT [PK_przejazdkursprzystanekwlini] PRIMARY KEY CLUSTERED 
(
	[przejazdkursprzystanekwlini_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[przejazdy]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[przejazdy](
	[przejazd_id] [bigint] IDENTITY(1,1) NOT NULL,
	[kierowca_id] [bigint] NOT NULL,
	[autobus_id] [bigint] NOT NULL,
	[kurs_id] [bigint] NOT NULL,
	[spalanie] [real] NULL,
	[cena_za_litr] [real] NULL,
	[dlugosc_trasy] [real] NULL,
	[data_startu] [datetime] NULL,
	[data_konca] [datetime] NULL,
	[data] [date] NULL,
 CONSTRAINT [PK_przejazdy] PRIMARY KEY CLUSTERED 
(
	[przejazd_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[przystanekwlini]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[przystanekwlini](
	[przystanekwlini_id] [bigint] IDENTITY(1,1) NOT NULL,
	[przystanek_id] [bigint] NOT NULL,
	[linia_id] [bigint] NOT NULL,
	[kolejnosc] [smallint] NOT NULL,
 CONSTRAINT [PK_przystanekwlini] PRIMARY KEY CLUSTERED 
(
	[przystanekwlini_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[przystanki]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[przystanki](
	[przystanek_id] [bigint] IDENTITY(1,1) NOT NULL,
	[przystanek_odwrotny_id] [bigint] NULL,
	[nazwa] [varchar](50) NULL,
	[kod_pocztowy] [varchar](20) NULL,
	[miasto] [varchar](50) NULL,
	[ulica] [varchar](50) NULL,
	[dlugosc_geograficzna] [varchar](20) NULL,
	[szerokosc_geograficzna] [varchar](20) NULL,
 CONSTRAINT [PK_przystanki] PRIMARY KEY CLUSTERED 
(
	[przystanek_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 18.06.2024 13:00:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[login] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[roles] [varbinary](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[incydenty]  WITH CHECK ADD  CONSTRAINT [FK_incydenty_autobusy] FOREIGN KEY([autobus_id])
REFERENCES [dbo].[autobusy] ([autbous_id])
GO
ALTER TABLE [dbo].[incydenty] CHECK CONSTRAINT [FK_incydenty_autobusy]
GO
ALTER TABLE [dbo].[incydenty]  WITH CHECK ADD  CONSTRAINT [FK_incydenty_kierowcy] FOREIGN KEY([kierowca_id])
REFERENCES [dbo].[kierowcy] ([kierowca_id])
GO
ALTER TABLE [dbo].[incydenty] CHECK CONSTRAINT [FK_incydenty_kierowcy]
GO
ALTER TABLE [dbo].[incydenty]  WITH CHECK ADD  CONSTRAINT [FK_incydenty_przejazdy] FOREIGN KEY([przejazd_id])
REFERENCES [dbo].[przejazdy] ([przejazd_id])
GO
ALTER TABLE [dbo].[incydenty] CHECK CONSTRAINT [FK_incydenty_przejazdy]
GO
ALTER TABLE [dbo].[kierowcy]  WITH CHECK ADD  CONSTRAINT [FK_kierowcy_users] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[kierowcy] CHECK CONSTRAINT [FK_kierowcy_users]
GO
ALTER TABLE [dbo].[kursprzystanekwlini]  WITH CHECK ADD  CONSTRAINT [FK_kurs_kursprzystanekwlini] FOREIGN KEY([kurs_id])
REFERENCES [dbo].[kursy] ([kurs_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[kursprzystanekwlini] CHECK CONSTRAINT [FK_kurs_kursprzystanekwlini]
GO
ALTER TABLE [dbo].[kursprzystanekwlini]  WITH CHECK ADD  CONSTRAINT [FK_kursprzystanekwlini_przystanekwlini] FOREIGN KEY([przystanekwlini_id])
REFERENCES [dbo].[przystanekwlini] ([przystanekwlini_id])
GO
ALTER TABLE [dbo].[kursprzystanekwlini] CHECK CONSTRAINT [FK_kursprzystanekwlini_przystanekwlini]
GO
ALTER TABLE [dbo].[kursy]  WITH CHECK ADD  CONSTRAINT [FK_kursy_kursy] FOREIGN KEY([harmonogram_id])
REFERENCES [dbo].[harmonogramy] ([hamonogram_id])
GO
ALTER TABLE [dbo].[kursy] CHECK CONSTRAINT [FK_kursy_kursy]
GO
ALTER TABLE [dbo].[kursy]  WITH CHECK ADD  CONSTRAINT [FK_kursy_linie] FOREIGN KEY([linia_id])
REFERENCES [dbo].[linie] ([linia_id])
GO
ALTER TABLE [dbo].[kursy] CHECK CONSTRAINT [FK_kursy_linie]
GO
ALTER TABLE [dbo].[przejazdbilet]  WITH CHECK ADD  CONSTRAINT [FK_przejazdbilet_bilety] FOREIGN KEY([bilet_id])
REFERENCES [dbo].[bilety] ([bilet_id])
GO
ALTER TABLE [dbo].[przejazdbilet] CHECK CONSTRAINT [FK_przejazdbilet_bilety]
GO
ALTER TABLE [dbo].[przejazdbilet]  WITH CHECK ADD  CONSTRAINT [FK_przejazdbilet_przejazdy] FOREIGN KEY([przejazd_id])
REFERENCES [dbo].[przejazdy] ([przejazd_id])
GO
ALTER TABLE [dbo].[przejazdbilet] CHECK CONSTRAINT [FK_przejazdbilet_przejazdy]
GO
ALTER TABLE [dbo].[przejazdkursprzystanekwlini]  WITH CHECK ADD  CONSTRAINT [FK_przejazdkursprzystanekwlini_kursprzystanekwlini] FOREIGN KEY([kursprzystanekwlini_id])
REFERENCES [dbo].[kursprzystanekwlini] ([kursprzystankiwlini_id])
GO
ALTER TABLE [dbo].[przejazdkursprzystanekwlini] CHECK CONSTRAINT [FK_przejazdkursprzystanekwlini_kursprzystanekwlini]
GO
ALTER TABLE [dbo].[przejazdkursprzystanekwlini]  WITH CHECK ADD  CONSTRAINT [FK_przejazdkursprzystanekwlini_przejazdy] FOREIGN KEY([przejazd_id])
REFERENCES [dbo].[przejazdy] ([przejazd_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[przejazdkursprzystanekwlini] CHECK CONSTRAINT [FK_przejazdkursprzystanekwlini_przejazdy]
GO
ALTER TABLE [dbo].[przejazdy]  WITH CHECK ADD  CONSTRAINT [FK_przejazdy_autobusy] FOREIGN KEY([autobus_id])
REFERENCES [dbo].[autobusy] ([autbous_id])
GO
ALTER TABLE [dbo].[przejazdy] CHECK CONSTRAINT [FK_przejazdy_autobusy]
GO
ALTER TABLE [dbo].[przejazdy]  WITH CHECK ADD  CONSTRAINT [FK_przejazdy_kierowcy] FOREIGN KEY([kierowca_id])
REFERENCES [dbo].[kierowcy] ([kierowca_id])
GO
ALTER TABLE [dbo].[przejazdy] CHECK CONSTRAINT [FK_przejazdy_kierowcy]
GO
ALTER TABLE [dbo].[przejazdy]  WITH CHECK ADD  CONSTRAINT [FK_przejazdy_kursy] FOREIGN KEY([kurs_id])
REFERENCES [dbo].[kursy] ([kurs_id])
GO
ALTER TABLE [dbo].[przejazdy] CHECK CONSTRAINT [FK_przejazdy_kursy]
GO
ALTER TABLE [dbo].[przystanekwlini]  WITH CHECK ADD  CONSTRAINT [FK_przystanekwlini_linie] FOREIGN KEY([linia_id])
REFERENCES [dbo].[linie] ([linia_id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[przystanekwlini] CHECK CONSTRAINT [FK_przystanekwlini_linie]
GO
ALTER TABLE [dbo].[przystanekwlini]  WITH CHECK ADD  CONSTRAINT [FK_przystanekwlini_przystanki1] FOREIGN KEY([przystanek_id])
REFERENCES [dbo].[przystanki] ([przystanek_id])
GO
ALTER TABLE [dbo].[przystanekwlini] CHECK CONSTRAINT [FK_przystanekwlini_przystanki1]
GO
ALTER TABLE [dbo].[przystanki]  WITH CHECK ADD  CONSTRAINT [FK_przystanki_przystanki] FOREIGN KEY([przystanek_odwrotny_id])
REFERENCES [dbo].[przystanki] ([przystanek_id])
GO
ALTER TABLE [dbo].[przystanki] CHECK CONSTRAINT [FK_przystanki_przystanki]
GO
USE [master]
GO
ALTER DATABASE [sopkom] SET  READ_WRITE 
GO