--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 12.4

-- Started on 2021-11-11 23:38:54

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 24577)
-- Name: mobile_numbers; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.mobile_numbers (
    id uuid NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    in_request boolean,
    is_ported boolean,
    number character varying(11),
    range_holder character varying(255),
    subscriber_id character varying(255),
    subscriber_name character varying(255),
    operator_id uuid
);


ALTER TABLE public.mobile_numbers OWNER TO root;

--
-- TOC entry 203 (class 1259 OID 24585)
-- Name: operators; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.operators (
    id uuid NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    number_prefix character varying(3),
    name character varying(255)
);


ALTER TABLE public.operators OWNER TO root;

--
-- TOC entry 204 (class 1259 OID 24590)
-- Name: porting_requests; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.porting_requests (
    id uuid NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    mobile_number character varying(255),
    status character varying(255),
    donor_name uuid,
    recipient_name uuid
);


ALTER TABLE public.porting_requests OWNER TO root;

--
-- TOC entry 2836 (class 0 OID 24577)
-- Dependencies: 202
-- Data for Name: mobile_numbers; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.mobile_numbers (id, created_at, updated_at, in_request, is_ported, number, range_holder, subscriber_id, subscriber_name, operator_id) FROM stdin;
a3120155-9c36-44b3-b562-e962dbeda512	2021-11-11 20:31:44.751205	2021-11-11 20:31:44.751205	f	f	01122340009	etisalat	012345678912	assem	1b5856b2-2d8d-4df0-8e1d-44119270915a
997b8853-5e18-49fa-9c77-88ade6316436	2021-11-11 20:52:45.430065	2021-11-11 20:52:45.430065	f	f	01012966929	vodafone	012345678912012345678912	4gtss	012f215c-6d95-4663-a02c-2f0f6b08c3c5
c0abfae9-546d-43ee-a53e-c42b1f0244ab	2021-11-11 20:53:04.351254	2021-11-11 20:53:04.351254	f	f	01012966939	vodafone	012345678912012345678912	4gtss	012f215c-6d95-4663-a02c-2f0f6b08c3c5
d29cdb5a-f04b-4318-8e17-d34c54bdb068	2021-11-11 20:53:12.045176	2021-11-11 20:53:12.045176	f	f	01012966839	vodafone	012345678912012345678912	4gtss	012f215c-6d95-4663-a02c-2f0f6b08c3c5
c48b96fb-fc4f-47af-8df1-55b16d019ed8	2021-11-11 20:53:55.18571	2021-11-11 20:53:55.18571	f	f	01212966565	orange	012345678912012345678912	4gtss	8c2a6999-c488-47b2-a3ee-e609c83f785e
a1446c02-caad-4433-89c9-d414041e9b20	2021-11-11 20:54:10.315974	2021-11-11 20:54:10.315974	f	f	01212966777	orange	012345678912012345678912	4gtss	8c2a6999-c488-47b2-a3ee-e609c83f785e
ae6a1aa6-07c2-479c-9ae1-363f420e4e34	2021-11-11 20:28:32.596638	2021-11-11 23:13:55.803681	t	f	01122340008	etisalat	012345678912012345678913332	4gtssssfgfg	1b5856b2-2d8d-4df0-8e1d-44119270915a
ede9ce7b-5aab-4586-93cd-251280a86ca8	2021-11-11 20:54:16.831786	2021-11-11 23:25:47.932602	f	t	01212966888	orange	012345678912012345678912	4gtss	012f215c-6d95-4663-a02c-2f0f6b08c3c5
809d4e6b-904a-480e-9592-a4ba1a162984	2021-11-11 20:29:03.791213	2021-11-11 23:36:56.460791	t	f	01122340004	etisalat	012345678912	assem	1b5856b2-2d8d-4df0-8e1d-44119270915a
\.


--
-- TOC entry 2837 (class 0 OID 24585)
-- Dependencies: 203
-- Data for Name: operators; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.operators (id, created_at, updated_at, number_prefix, name) FROM stdin;
012f215c-6d95-4663-a02c-2f0f6b08c3c5	2021-11-09 23:38:19.448859	2021-11-09 23:38:19.454842	010	vodafone
1b5856b2-2d8d-4df0-8e1d-44119270915a	2021-11-11 20:00:29.209342	2021-11-11 20:00:29.217319	011	etisalat
8c2a6999-c488-47b2-a3ee-e609c83f785e	2021-11-11 20:00:41.940965	2021-11-11 20:00:41.941963	012	orange
\.


--
-- TOC entry 2838 (class 0 OID 24590)
-- Dependencies: 204
-- Data for Name: porting_requests; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.porting_requests (id, created_at, updated_at, mobile_number, status, donor_name, recipient_name) FROM stdin;
42cdcddc-38c7-49c8-8e10-8cc13a2160f7	2021-11-11 22:10:42.439452	2021-11-11 22:48:15.840498	01122340002	CANCELED	1b5856b2-2d8d-4df0-8e1d-44119270915a	012f215c-6d95-4663-a02c-2f0f6b08c3c5
597c47a4-4252-42fa-8a29-c09071ccbb0b	2021-11-11 22:24:18.398789	2021-11-11 22:48:15.846482	01122340002	CANCELED	1b5856b2-2d8d-4df0-8e1d-44119270915a	012f215c-6d95-4663-a02c-2f0f6b08c3c5
fb8d23b9-f34c-4b64-bd7d-63e536cd227f	2021-11-11 22:27:51.06994	2021-11-11 22:48:15.847479	01122340002	CANCELED	1b5856b2-2d8d-4df0-8e1d-44119270915a	012f215c-6d95-4663-a02c-2f0f6b08c3c5
478f8ce8-8a2d-4569-9acc-0dbed1b73f8c	2021-11-11 22:29:17.806419	2021-11-11 22:48:15.848477	01122340002	CANCELED	1b5856b2-2d8d-4df0-8e1d-44119270915a	012f215c-6d95-4663-a02c-2f0f6b08c3c5
53d7164a-cac1-46fc-a1d8-f05f72706d87	2021-11-11 22:30:33.258819	2021-11-11 22:48:15.848477	01122340002	CANCELED	1b5856b2-2d8d-4df0-8e1d-44119270915a	012f215c-6d95-4663-a02c-2f0f6b08c3c5
1d4e2f97-ab66-4cf2-89cb-9578b3ed0ac0	2021-11-11 22:32:39.046115	2021-11-11 22:48:15.849475	01122340002	CANCELED	1b5856b2-2d8d-4df0-8e1d-44119270915a	012f215c-6d95-4663-a02c-2f0f6b08c3c5
f816b83e-5885-45a4-b3e8-56abac9c8e09	2021-11-11 23:13:55.799691	2021-11-11 23:15:55.871155	01122340008	CANCELED	1b5856b2-2d8d-4df0-8e1d-44119270915a	012f215c-6d95-4663-a02c-2f0f6b08c3c5
c48369c6-7c78-4a53-a78c-8b7a0097bbae	2021-11-11 23:23:36.534001	2021-11-11 23:25:47.931566	01212966888	ACCEPTED	8c2a6999-c488-47b2-a3ee-e609c83f785e	012f215c-6d95-4663-a02c-2f0f6b08c3c5
06678e7a-fc98-4902-9594-822fb1d3a775	2021-11-11 23:27:12.696918	2021-11-11 23:27:51.482693	01122340004	REJECTED	1b5856b2-2d8d-4df0-8e1d-44119270915a	012f215c-6d95-4663-a02c-2f0f6b08c3c5
9cab0db1-bc7c-446e-aca9-864ab28416c7	2021-11-11 23:36:56.45676	2021-11-11 23:36:56.45676	01122340004	PENDING	1b5856b2-2d8d-4df0-8e1d-44119270915a	012f215c-6d95-4663-a02c-2f0f6b08c3c5
\.


--
-- TOC entry 2696 (class 2606 OID 24584)
-- Name: mobile_numbers mobile_numbers_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.mobile_numbers
    ADD CONSTRAINT mobile_numbers_pkey PRIMARY KEY (id);


--
-- TOC entry 2700 (class 2606 OID 24589)
-- Name: operators operators_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.operators
    ADD CONSTRAINT operators_pkey PRIMARY KEY (id);


--
-- TOC entry 2706 (class 2606 OID 24597)
-- Name: porting_requests porting_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.porting_requests
    ADD CONSTRAINT porting_requests_pkey PRIMARY KEY (id);


--
-- TOC entry 2702 (class 2606 OID 24603)
-- Name: operators uk_3n4ukg72cjnmbhkrc44ehc7xg; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.operators
    ADD CONSTRAINT uk_3n4ukg72cjnmbhkrc44ehc7xg UNIQUE (name);


--
-- TOC entry 2704 (class 2606 OID 24601)
-- Name: operators uk_laufp00h949ybbmji8tw1boie; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.operators
    ADD CONSTRAINT uk_laufp00h949ybbmji8tw1boie UNIQUE (number_prefix);


--
-- TOC entry 2698 (class 2606 OID 24599)
-- Name: mobile_numbers uk_tgvf730rrn9qxpfhre5hyx4r7; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.mobile_numbers
    ADD CONSTRAINT uk_tgvf730rrn9qxpfhre5hyx4r7 UNIQUE (number);


--
-- TOC entry 2709 (class 2606 OID 24614)
-- Name: porting_requests fkj13pnys3kh1k57w853akninvb; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.porting_requests
    ADD CONSTRAINT fkj13pnys3kh1k57w853akninvb FOREIGN KEY (recipient_name) REFERENCES public.operators(id);


--
-- TOC entry 2708 (class 2606 OID 24609)
-- Name: porting_requests fkoktfgbn64j3432863p3bbtlj8; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.porting_requests
    ADD CONSTRAINT fkoktfgbn64j3432863p3bbtlj8 FOREIGN KEY (donor_name) REFERENCES public.operators(id);


--
-- TOC entry 2707 (class 2606 OID 24604)
-- Name: mobile_numbers fkplhghdlx0bx24vrjugysc2gxo; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.mobile_numbers
    ADD CONSTRAINT fkplhghdlx0bx24vrjugysc2gxo FOREIGN KEY (operator_id) REFERENCES public.operators(id);


-- Completed on 2021-11-11 23:38:54

--
-- PostgreSQL database dump complete
--

