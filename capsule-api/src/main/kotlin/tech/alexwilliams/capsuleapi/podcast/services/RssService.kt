package tech.alexwilliams.capsuleapi.podcast.services

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.XStreamException
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import reactor.core.publisher.Mono
import tech.alexwilliams.capsuleapi.podcast.config.xml.XstreamInitializer
import tech.alexwilliams.capsuleapi.podcast.exception.PodcastRssFeedException
import tech.alexwilliams.capsuleapi.podcast.models.PodcastRssFeed
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono


@Service
class RssService {
    private var xStream: XStream? = null

    init {
        val xStreamInitializer = XstreamInitializer()
        this.xStream = xStreamInitializer.getXstreamInstance()
    }

    fun fetchRssFeed(rssUrl: String): Mono<String> {
        val client = WebClient.create(rssUrl)
        return client.get()
                        .accept(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_RSS_XML)
                        .retrieve().bodyToMono<String>().map { data ->
                            println(data)

                            data
        }
    }

    fun deserializePodcastRssFeed(rssFeed: String): PodcastRssFeed {
        try {
           // val str = rssFeed.replace("""\r?\n|\r""".toRegex(), "")
               val test = this.test()
            return xStream!!.fromXML(test) as PodcastRssFeed
        } catch (ex: XStreamException) { // TODO: add logging framework
            throw PodcastRssFeedException("Error deserializing podcast rss feed")
        } catch (ex: Exception) { // TODO: add logging framework
            throw PodcastRssFeedException("Error deserializing podcast rss feed")
        }
    }

    fun test(): String {
        return """<?xml version="1.0" encoding="UTF-8"?>
<rss version="2.0" xmlns:itunes="http://www.itunes.com/dtds/podcast-1.0.dtd" xmlns:googleplay="http://www.google.com/schemas/play-podcasts/1.0" xmlns:atom="http://www.w3.org/2005/Atom" xmlns:media="http://search.yahoo.com/mrss/" xmlns:content="http://purl.org/rss/1.0/modules/content/">
  <channel>
    <atom:link href="https://feeds.megaphone.fm/HSW7536705545" rel="self" type="application/rss+xml"/>
    <title>This Is Important</title>
    <language>en</language>
    <copyright>iHeartRadio</copyright>
    <description>Adam Devine, Anders Holm, Blake Anderson, and Kyle Newacheck seriously discuss some very important topics.</description>
    <image>
      <url>https://images.megaphone.fm/rZ6ea4jsgPymP37aUPgjAhT48ea7zNYON0pOp1f2xeA/plain/s3://megaphone-prod/podcasts/20422d96-eb9d-11ea-91b5-933f5d1079e2/image/uploads_2F1602180999917-t0707z9l2q-09161b2f84545593881c7326da5efc5a_2FThis%2Bis%2BImportant%2BFinal%2B3000x3000%2BiHR.jpg</url>
      <title>This Is Important</title>
    </image>
    <itunes:explicit>yes</itunes:explicit>
    <itunes:type>episodic</itunes:type>
    <itunes:subtitle></itunes:subtitle>
    <itunes:author>iHeartRadio</itunes:author>
    <itunes:summary>Adam Devine, Anders Holm, Blake Anderson, and Kyle Newacheck seriously discuss some very important topics.</itunes:summary>
    <content:encoded>
      <![CDATA[<p>Adam Devine, Anders Holm, Blake Anderson, and Kyle Newacheck seriously discuss some very important topics.</p>]]>
    </content:encoded>
    <itunes:owner>
      <itunes:name>iHeartRadio</itunes:name>
      <itunes:email>applepodcast@howstuffworks.com</itunes:email>
    </itunes:owner>
    <itunes:image href="https://images.megaphone.fm/rZ6ea4jsgPymP37aUPgjAhT48ea7zNYON0pOp1f2xeA/plain/s3://megaphone-prod/podcasts/20422d96-eb9d-11ea-91b5-933f5d1079e2/image/uploads_2F1602180999917-t0707z9l2q-09161b2f84545593881c7326da5efc5a_2FThis%2Bis%2BImportant%2BFinal%2B3000x3000%2BiHR.jpg"/>
    <itunes:category text="Comedy">
    </itunes:category>
    <itunes:category text="Society &amp; Culture">
    </itunes:category>
    <item>
      <title>Ep 28: How The Dudes Got Banned From Comic Con</title>
      <description>Today, this is important:
Acne, friends who got coronavirus, movies, Richard Dreyfuss, car tricks, breaking the rules in their youth, horny girls, dressing like Kevin Federline, Comic Con adventures, Kyle's under wraps car, music they fucked with back in the day, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 02 Mar 2021 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>28</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is important:
Acne, friends who got coronavirus, movies, Richard Dreyfuss, car tricks, breaking the rules in their youth, horny girls, dressing like Kevin Federline, Comic Con adventures, Kyle's under wraps car, music they fucked with back in the day, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is important:</p><p>Acne, friends who got coronavirus, movies, Richard Dreyfuss, car tricks, breaking the rules in their youth, horny girls, dressing like Kevin Federline, Comic Con adventures, Kyle's under wraps car, music they fucked with back in the day, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4431</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c0b43b5c-0d12-11eb-87d3-9b8528df3eb0]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW1394351903.mp3?updated=1614653493" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 27: Have You Bought Our Merch Yet? </title>
      <description>Today, this is what's important:
The Super Bowl, Bruno Mars, party music, Affliction, fashion looks coming back, ASMR, merch, carnival games, repping college gear, childhood profanities, the birds and the bees, sex education classes, Adam creeping on his friends hooking up, smoking cigarettes, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 23 Feb 2021 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>27</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what's important:
The Super Bowl, Bruno Mars, party music, Affliction, fashion looks coming back, ASMR, merch, carnival games, repping college gear, childhood profanities, the birds and the bees, sex education classes, Adam creeping on his friends hooking up, smoking cigarettes, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what's important:</p><p>The Super Bowl, Bruno Mars, party music, Affliction, fashion looks coming back, ASMR, merch, carnival games, repping college gear, childhood profanities, the birds and the bees, sex education classes, Adam creeping on his friends hooking up, smoking cigarettes, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4376</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c0b038ae-0d12-11eb-87d3-0f925c38f6b3]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW1753218065.mp3?updated=1614041727" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 26: Why Adam’s Groomsmen Need To Be Good With A Sword</title>
      <description>Today, this is what's important:
Groomsmen, podcasts they listen to, Qanon, SNL jokes, Kyle's background work on CSI, Mike McCoy, British accents, Adam's favorite actor, merch, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 16 Feb 2021 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>26</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what's important:
Groomsmen, podcasts they listen to, Qanon, SNL jokes, Kyle's background work on CSI, Mike McCoy, British accents, Adam's favorite actor, merch, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what's important:</p><p>Groomsmen, podcasts they listen to, Qanon, SNL jokes, Kyle's background work on CSI, Mike McCoy, British accents, Adam's favorite actor, merch, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4205</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c0ac789a-0d12-11eb-87d3-d7a3979e0b51]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW3849972601.mp3?updated=1613432302" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 25: The Guys Star in Snap Crackle and Pop The Movie</title>
      <description>Today, this is what's important:
Space, cereal, The Goonies, films the guys have worked on as extras before they were famous, high school theater and improv, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 09 Feb 2021 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>25</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what's important:
Space, cereal, The Goonies, films the guys have worked on as extras before they were famous, high school theater and improv, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what's important:</p><p>Space, cereal, The Goonies, films the guys have worked on as extras before they were famous, high school theater and improv, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4302</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c0a89db0-0d12-11eb-87d3-bf91153266a2]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW3473355640.mp3?updated=1612846696" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 24: The Dudes Consider Doing a Steroid Challenge</title>
      <description>Today, this is what's important:
Mexico, pain pills, drugs, production during covid, puzzles, childhood home decor, birds, owls, smoking real weed while filming Workaholics, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 02 Feb 2021 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>24</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what's important:
Mexico, pain pills, drugs, production during covid, puzzles, childhood home decor, birds, owls, smoking real weed while filming Workaholics, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what's important:</p><p>Mexico, pain pills, drugs, production during covid, puzzles, childhood home decor, birds, owls, smoking real weed while filming Workaholics, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4320</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c0a3adf0-0d12-11eb-87d3-73a6bfd2ce00]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW9446673957.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 23: Dumb Adventures In Bad Fake IDs</title>
      <description>Today, this is what's important:
Merch, dad hats, the Cactus Club, slow motion/timelapse, different types of marijuana intake, hangover stories, crying while drunk, fake ID's, headshots, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 26 Jan 2021 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>23</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what's important:
Merch, dad hats, the Cactus Club, slow motion/timelapse, different types of marijuana intake, hangover stories, crying while drunk, fake ID's, headshots, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what's important:</p><p>Merch, dad hats, the Cactus Club, slow motion/timelapse, different types of marijuana intake, hangover stories, crying while drunk, fake ID's, headshots, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4165</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c09fea44-0d12-11eb-87d3-27fde78e76f9]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW9830121997.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 22: A Message Specifically For Taylor Swift</title>
      <description>Today, this is what's important:
Yelling 'let's go,' Adam's ass winning a twitter poll, biking gear, the Mandela Effect, the Oculus Rift, Taylor Swift wearing the bear coat, Peaky Blinders, The Brady Bunch, Adam's character on The Righteous Gemstones, making money off religion, the ultimate urinal prank, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 19 Jan 2021 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>22</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what's important:
Yelling 'let's go,' Adam's ass winning a twitter poll, biking gear, the Mandela Effect, the Oculus Rift, Taylor Swift wearing the bear coat, Peaky Blinders, The Brady Bunch, Adam's character on The Righteous Gemstones, making money off religion, the ultimate urinal prank, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what's important:</p><p>Yelling 'let's go,' Adam's ass winning a twitter poll, biking gear, the Mandela Effect, the Oculus Rift, Taylor Swift wearing the bear coat, Peaky Blinders, The Brady Bunch, Adam's character on The Righteous Gemstones, making money off religion, the ultimate urinal prank, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4509</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c09bf9ac-0d12-11eb-87d3-b39a79fa2164]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW8707368986.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 21: Tales From The Workaholics House</title>
      <description>Today, this is what’s important:
Adam almost dying, what's going on in Primm, Nevada, The Bunny Ranch, the infamous pizza in the butt and voicemail story, paint balling, behind the scenes of Workaholics, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 12 Jan 2021 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>21</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Adam almost dying, what's going on in Primm, Nevada, The Bunny Ranch, the infamous pizza in the butt and voicemail story, paint balling, behind the scenes of Workaholics, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Adam almost dying, what's going on in Primm, Nevada, The Bunny Ranch, the infamous pizza in the butt and voicemail story, paint balling, behind the scenes of Workaholics, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4653</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c097dc3c-0d12-11eb-87d3-4fe463e2eebb]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW9299247248.mp3?updated=1610431751" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 20: In Defense Of Mario Lopez As Colonel Sanders</title>
      <description>Today, this is what’s important:
iOS Big Sur, driving on the highway 1, brand collabs, Anders as baby peanut, Kyle leaving LA, PornHub, Momo, the band Cake, Der's bowing out, Adam's fitness journey, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 05 Jan 2021 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>20</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
iOS Big Sur, driving on the highway 1, brand collabs, Anders as baby peanut, Kyle leaving LA, PornHub, Momo, the band Cake, Der's bowing out, Adam's fitness journey, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>iOS Big Sur, driving on the highway 1, brand collabs, Anders as baby peanut, Kyle leaving LA, PornHub, Momo, the band Cake, Der's bowing out, Adam's fitness journey, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4378</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c09438e8-0d12-11eb-87d3-3f288f5944aa]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW8526398232.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 19: Ranking the Guys’ Butts from Best To Worst</title>
      <description>Today, this is what’s important:
Wrestlers in political positions, Hedonism, Adam's old job at the Hollywood Improv, how to remember peoples names, the truth behind DeVine, who's got the best butt, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 29 Dec 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>19</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Wrestlers in political positions, Hedonism, Adam's old job at the Hollywood Improv, how to remember peoples names, the truth behind DeVine, who's got the best butt, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Wrestlers in political positions, Hedonism, Adam's old job at the Hollywood Improv, how to remember peoples names, the truth behind DeVine, who's got the best butt, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4086</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c0902fbe-0d12-11eb-87d3-7388002bf0ac]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW2688800877.mp3?updated=1609202135" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 18: Blake's December To Remember </title>
      <description>Today, this is what’s important:
Staying fit with Adam, Blake's sober December, The Masked Singer, actors the guys found attractive in their youth, Kyle's top films, hot boy roles, dark boy roles, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 22 Dec 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>18</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Staying fit with Adam, Blake's sober December, The Masked Singer, actors the guys found attractive in their youth, Kyle's top films, hot boy roles, dark boy roles, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Staying fit with Adam, Blake's sober December, The Masked Singer, actors the guys found attractive in their youth, Kyle's top films, hot boy roles, dark boy roles, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4292</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c08c0e5c-0d12-11eb-87d3-e7136380562f]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW5733974659.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 17: The Dudes Get A Little Drunk</title>
      <description>Today, this is what’s important:
Working on set during covid, Thanksgiving, Amish folk, a break down of who is winning the Grammy's Best Song category, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 15 Dec 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>17</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Working on set during covid, Thanksgiving, Amish folk, a break down of who is winning the Grammy's Best Song category, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Working on set during covid, Thanksgiving, Amish folk, a break down of who is winning the Grammy's Best Song category, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4051</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c0885ef6-0d12-11eb-87d3-af84b2998266]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW2279530171.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 16: You Are Wiping Your Ass Wrong</title>
      <description>Today, this is what’s important:
Parents listening to this the podcast, Adam almost murdering someone, game consoles, The Foot Fist Way, Sinbad, underwear, how to wipe your ass, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Thu, 10 Dec 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>16</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Parents listening to this the podcast, Adam almost murdering someone, game consoles, The Foot Fist Way, Sinbad, underwear, how to wipe your ass, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Parents listening to this the podcast, Adam almost murdering someone, game consoles, The Foot Fist Way, Sinbad, underwear, how to wipe your ass, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>3988</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[efe60778-3a87-11eb-a4b5-476688ca2584]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW2289967508.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 15: The Dudes Exchange (Weird) Tales Of Cyber Sex</title>
      <description>Today, this is what’s important:
ASMR, Adam's multi-cam sitcom, podcast feedback, cyber sex, imagining you will be scouted, what weapon you would bring to a fight, Adam being a bully, Mark Wahlberg, and more. 
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 08 Dec 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>15</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
ASMR, Adam's multi-cam sitcom, podcast feedback, cyber sex, imagining you will be scouted, what weapon you would bring to a fight, Adam being a bully, Mark Wahlberg, and more. 
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>ASMR, Adam's multi-cam sitcom, podcast feedback, cyber sex, imagining you will be scouted, what weapon you would bring to a fight, Adam being a bully, Mark Wahlberg, and more. </p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>3927</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c084b670-0d12-11eb-87d3-b7acdce8cdae]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW1018387135.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 14: Which Chain Pizza Rules Them All?</title>
      <description>Today, this is what’s important:
More hard seltzer, halloween candy, Who Get's The Dog?, film school, Kyle's lonely years, dating roommates, sex with the guys, Kyle's hair, manscaping, TikTok, Pizza rankings, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Thu, 03 Dec 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>14</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
More hard seltzer, halloween candy, Who Get's The Dog?, film school, Kyle's lonely years, dating roommates, sex with the guys, Kyle's hair, manscaping, TikTok, Pizza rankings, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>More hard seltzer, halloween candy, Who Get's The Dog?, film school, Kyle's lonely years, dating roommates, sex with the guys, Kyle's hair, manscaping, TikTok, Pizza rankings, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4068</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[48b78b4c-2480-11eb-894d-c7f075118656]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW6694130400.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 13: Which Of The Guys Would Do The Best In Prison? </title>
      <description>Today, this is what’s important:
Hard seltzer, pornography, which guy will live the longest, John McAfee, Insane Clown Posse, super hero gender swapping, cross-fit Adam, Free Karl, prison culture, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 01 Dec 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>13</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Hard seltzer, pornography, which guy will live the longest, John McAfee, Insane Clown Posse, super hero gender swapping, cross-fit Adam, Free Karl, prison culture, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Hard seltzer, pornography, which guy will live the longest, John McAfee, Insane Clown Posse, super hero gender swapping, cross-fit Adam, Free Karl, prison culture, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4076</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c0806cb4-0d12-11eb-87d3-3b0fd7c196e3]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW8356383767.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 12: That Time Blake Ate Lasagna With The Edge From U2</title>
      <description>Today, this is what’s important:
Rap snacks, convenience store snacks, G-Money, break-ins at the guys old place, Blake's driving, stick-shift, motorcycles, Blake having dinner with The Edge, protest music, the MTV Music Awards, your mom's cooking, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Thu, 26 Nov 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>12</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Rap snacks, convenience store snacks, G-Money, break-ins at the guys old place, Blake's driving, stick-shift, motorcycles, Blake having dinner with The Edge, protest music, the MTV Music Awards, your mom's cooking, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Rap snacks, convenience store snacks, G-Money, break-ins at the guys old place, Blake's driving, stick-shift, motorcycles, Blake having dinner with The Edge, protest music, the MTV Music Awards, your mom's cooking, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4089</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[48b3b922-2480-11eb-894d-a3199902e161]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW4537205417.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 11: You’re Either a B***h or an A**hole - Which of the Guys is What?</title>
      <description>Today, this is what’s important:
Asshole versus bitch, biopics, smart vocabulary words, Robin Williams, Last Man Standing, Entourage, crying to television/movies, Kyle's almost major leagues baseball career, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 24 Nov 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>11</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Asshole versus bitch, biopics, smart vocabulary words, Robin Williams, Last Man Standing, Entourage, crying to television/movies, Kyle's almost major leagues baseball career, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Asshole versus bitch, biopics, smart vocabulary words, Robin Williams, Last Man Standing, Entourage, crying to television/movies, Kyle's almost major leagues baseball career, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>3523</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c07bcfc4-0d12-11eb-87d3-ab89b7a4272a]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW9063466330.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 10: The Best Reason To Grab An Animals Dick</title>
      <description>Today, this is what’s important:
Hiking, proud boy stolen fashions, fighting off a big cat, how to stop a pit-bull attack, celebrity sightings, family farts, high school fashions, thrift shopping, living with animals, rabbits, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Thu, 19 Nov 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>10</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Hiking, proud boy stolen fashions, fighting off a big cat, how to stop a pit-bull attack, celebrity sightings, family farts, high school fashions, thrift shopping, living with animals, rabbits, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Hiking, proud boy stolen fashions, fighting off a big cat, how to stop a pit-bull attack, celebrity sightings, family farts, high school fashions, thrift shopping, living with animals, rabbits, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4530</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[48b04dbe-2480-11eb-894d-23e0e5afdf39]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW1167776373.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 9: When Adam Almost OD’ed (As A Baby)</title>
      <description>Today, this is what’s important:
Parking tickets, male Karens, lethal workouts, Raising Cane's, franchising, salads, close calls to death, drunk driving, and more!
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 17 Nov 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>9</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Parking tickets, male Karens, lethal workouts, Raising Cane's, franchising, salads, close calls to death, drunk driving, and more!
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Parking tickets, male Karens, lethal workouts, Raising Cane's, franchising, salads, close calls to death, drunk driving, and more!</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>3216</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c0771088-0d12-11eb-87d3-7f73d505f397]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW1449698148.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 8: Beautiful Men With Bad D!@ks</title>
      <description>Today, this is what’s important:
The Emmys, Chris Elliott, Jared Leto, The Razzies, Robert DeNiro, Bo Jackson, Anders fighting the neighbors, believing in yourself, coffee, Adam's sleep habits, their drink of choice, Kombucha, and more!
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Thu, 12 Nov 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>8</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
The Emmys, Chris Elliott, Jared Leto, The Razzies, Robert DeNiro, Bo Jackson, Anders fighting the neighbors, believing in yourself, coffee, Adam's sleep habits, their drink of choice, Kombucha, and more!
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>The Emmys, Chris Elliott, Jared Leto, The Razzies, Robert DeNiro, Bo Jackson, Anders fighting the neighbors, believing in yourself, coffee, Adam's sleep habits, their drink of choice, Kombucha, and more!</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4483</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[48ab2136-2480-11eb-894d-47f95fb0c015]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW1367880495.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 7: Yeah But What Does Joe Biden SMELL Like? </title>
      <description>Today, this is what’s important:
Handwriting, pens, MDMA, TLC, TikTok, viral videos, Sturgis Motorcycle Rally, the Baldwin brothers, Kyle's back tattoo, piercings, jam bands, marathons, what politicians smell like, deodorant, nicknames, and more!
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 10 Nov 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>7</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Handwriting, pens, MDMA, TLC, TikTok, viral videos, Sturgis Motorcycle Rally, the Baldwin brothers, Kyle's back tattoo, piercings, jam bands, marathons, what politicians smell like, deodorant, nicknames, and more!
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Handwriting, pens, MDMA, TLC, TikTok, viral videos, Sturgis Motorcycle Rally, the Baldwin brothers, Kyle's back tattoo, piercings, jam bands, marathons, what politicians smell like, deodorant, nicknames, and more!</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>3931</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c0735e98-0d12-11eb-87d3-3f59bc5010aa]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW9103792613.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 6: What If Your Kink Is Getting Kink Shamed?</title>
      <description>Today, this is what’s important:
Wildfires, sex reveal parties, doctor misdiagnosis, Netflix DVDs, aesthetics, art, and symbols appropriated by white supremacy groups, satirical work, OnlyFans, cameo, sex hotlines, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 03 Nov 2020 11:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>6</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Wildfires, sex reveal parties, doctor misdiagnosis, Netflix DVDs, aesthetics, art, and symbols appropriated by white supremacy groups, satirical work, OnlyFans, cameo, sex hotlines, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Wildfires, sex reveal parties, doctor misdiagnosis, Netflix DVDs, aesthetics, art, and symbols appropriated by white supremacy groups, satirical work, OnlyFans, cameo, sex hotlines, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>3983</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c06f8674-0d12-11eb-87d3-17be72d8fc86]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW3997671656.mp3?updated=1604384545" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 5: The Dudes F#@%, Marry &amp; Kill Each Other! </title>
      <description>Today, this is what’s important:
The Ring camera, Adam being robbed, big cats, eating ass, animal trainers on set, fuck-marry-kill, taking back a death, Chadwick Boseman, artists who died too young, The Wizards, the SXSW Workaholics house party, high school teachers, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 27 Oct 2020 10:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>5</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
The Ring camera, Adam being robbed, big cats, eating ass, animal trainers on set, fuck-marry-kill, taking back a death, Chadwick Boseman, artists who died too young, The Wizards, the SXSW Workaholics house party, high school teachers, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>The Ring camera, Adam being robbed, big cats, eating ass, animal trainers on set, fuck-marry-kill, taking back a death, Chadwick Boseman, artists who died too young, The Wizards, the SXSW Workaholics house party, high school teachers, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>3882</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c06bc520-0d12-11eb-87d3-6f9be15894b1]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW6412291624.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 4: Best Super Soaker Size? to Heaviest D!@% In The NBA</title>
      <description>Today, this is what’s important:
Collectable cards, beefiest athletes, bike tricks, childhood neighborhood games, super soakers, backyard wrestling on public access tv, bullies, Tenet, Hard Knocks, baseball and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 20 Oct 2020 10:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>4</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Collectable cards, beefiest athletes, bike tricks, childhood neighborhood games, super soakers, backyard wrestling on public access tv, bullies, Tenet, Hard Knocks, baseball and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Collectable cards, beefiest athletes, bike tricks, childhood neighborhood games, super soakers, backyard wrestling on public access tv, bullies, Tenet, Hard Knocks, baseball and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>3859</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c068235c-0d12-11eb-87d3-f376226d05ec]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW8699420269.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 3: Hottest Hemsworth to Pizza Deliveryman Etiquette </title>
      <description>Today, this is what’s important:
Workouts, Dune, Hottest Hemsworth, Pizza Deliveryman etiquette, Pooping, Skrillex, Spoonerisms, Reactions videos, WAP, and more
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 13 Oct 2020 10:02:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>3</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Workouts, Dune, Hottest Hemsworth, Pizza Deliveryman etiquette, Pooping, Skrillex, Spoonerisms, Reactions videos, WAP, and more
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Workouts, Dune, Hottest Hemsworth, Pizza Deliveryman etiquette, Pooping, Skrillex, Spoonerisms, Reactions videos, WAP, and more</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>4273</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c062f5bc-0d12-11eb-87d3-5bbad8b155ed]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW5516908677.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 2: Male Karens to Wine Mouth </title>
      <description>Today, this is what’s important:
Bike accidents, Terrence Howard, Male Karens, Wine Mouth, Smoking, Elective Procedures, Foreskin, Liposuction, Music to have sex to, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 13 Oct 2020 10:01:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>2</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Bike accidents, Terrence Howard, Male Karens, Wine Mouth, Smoking, Elective Procedures, Foreskin, Liposuction, Music to have sex to, and more.
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Bike accidents, Terrence Howard, Male Karens, Wine Mouth, Smoking, Elective Procedures, Foreskin, Liposuction, Music to have sex to, and more.</p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>3947</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[c649007a-0d12-11eb-967f-23ed903818ad]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW2474699417.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Ep 1: Shark Week to Disney Movies</title>
      <description>Today, this is what’s important:
Shark Week, Pinocchio, Papa Johns, Dumbo, Tattoos, Hot or Not, High School, Kids, Covid, Hard Nipple Balls, Boners, Bullies, Ejaculation, and more. 
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Tue, 13 Oct 2020 10:00:00 -0000</pubDate>
      <itunes:episodeType>full</itunes:episodeType>
      <itunes:episode>1</itunes:episode>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Today, this is what’s important:
Shark Week, Pinocchio, Papa Johns, Dumbo, Tattoos, Hot or Not, High School, Kids, Covid, Hard Nipple Balls, Boners, Bullies, Ejaculation, and more. 
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Today, this is what’s important:</p><p>Shark Week, Pinocchio, Papa Johns, Dumbo, Tattoos, Hot or Not, High School, Kids, Covid, Hard Nipple Balls, Boners, Bullies, Ejaculation, and more. </p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>3604</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[cb3e5b16-0d12-11eb-b152-0be5f4c39c96]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW4583661415.mp3" length="0" type="audio/mpeg"/>
    </item>
    <item>
      <title>Introducing: This is Important</title>
      <description>Introducing: This Is Important. 
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</description>
      <pubDate>Thu, 08 Oct 2020 18:17:00 -0000</pubDate>
      <itunes:episodeType>trailer</itunes:episodeType>
      <itunes:author>iHeartRadio</itunes:author>
      <itunes:subtitle></itunes:subtitle>
      <itunes:summary>Introducing: This Is Important. 
 Learn more about your ad-choices at https://www.iheartpodcastnetwork.com</itunes:summary>
      <content:encoded>
        <![CDATA[<p>Introducing: This Is Important. </p><p> </p> Learn more about your ad-choices at <a href="https://www.iheartpodcastnetwork.com">https://www.iheartpodcastnetwork.com</a>]]>
      </content:encoded>
      <itunes:duration>116</itunes:duration>
      <itunes:explicit>yes</itunes:explicit>
      <guid isPermaLink="false"><![CDATA[9d020a40-eb9d-11ea-8c07-cb31e4e1d01b]]></guid>
      <enclosure url="https://www.podtrac.com/pts/redirect.mp3/chtbl.com/track/5899E/traffic.megaphone.fm/HSW4858033893.mp3?updated=1602275976" length="0" type="audio/mpeg"/>
    </item>
  </channel>
</rss>
"""
    }
}